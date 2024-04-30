package ru.strebkov.T1_SpringSecurityJwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.User;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtServiceRefreshToken генерирует рефреш-токен, который возвращается клиенту
 *  т.ж. для генерации нового access токена
 */
@Service
public class JwtServiceRefreshToken {

    @Value("${jwt.keyRefresh}")
    private String jwtSigningKeyRefresh;

    @Value("${jwt.refreshExpirationMs}")
    private Duration tokenExpirationRefresh;

    /**
     * Извлечение имени пользователя из токена
     *
     * @param refreshToken рефреш-токен
     * @return имя пользователя
     */
    public String extractUserNameR(String refreshToken) {
        return extractClaimR(refreshToken, Claims::getSubject);
    }

    /**
     * Генерация токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    public String generateTokenR(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("id", customUserDetails.getId());
        }
        return generateTokenR(claims, userDetails);
    }

    /**
     * Генерация токена
     *
     * @param extraClaims дополнительные данные
     * @param userDetails данные пользователя
     * @return токен
     */
    private String generateTokenR(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationRefresh.toMillis()))
                .signWith(getSigningKeyR(), SignatureAlgorithm.HS256).compact();
    }


    /**
     * Получение ключа для подписи токена
     *
     * @return ключ
     */
    private Key getSigningKeyR() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKeyRefresh);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Проверка токена на валидность
     *
     * @param refreshToken
     * @return   true, если токен валиден
     */
    public boolean isTokenValidR(String refreshToken) {
        final String userName = extractUserNameR(refreshToken);
        return !isTokenExpiredR(refreshToken);
    }

    /**
     * Извлечение данных из токена
     *
     * @param refreshToken           токен
     * @param claimsResolvers функция извлечения данных
     * @param <T>             тип данных
     * @return данные
     */
    private <T> T extractClaimR(String refreshToken, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaimsR(refreshToken);
        return claimsResolvers.apply(claims);
    }

    /**
     * Проверка токена на просроченность
     *
     * @param refreshToken токен
     * @return true, если токен просрочен
     */
    private boolean isTokenExpiredR(String refreshToken) {
        return extractExpirationR(refreshToken).before(new Date());
    }

    /**
     * Извлечение даты истечения токена
     *
     * @param refreshToken токен
     * @return дата истечения
     */
    private Date extractExpirationR(String refreshToken) {
        return extractClaimR(refreshToken, Claims::getExpiration);
    }

    /**
     * Извлечение всех данных из токена
     *
     * @param refreshToken токен
     * @return данные
     */
    private Claims extractAllClaimsR(String refreshToken) {
        return Jwts.parser().setSigningKey(getSigningKeyR()).build().parseClaimsJws(refreshToken)
                .getBody();
    }

}
