package ru.strebkov.T1_SpringSecurityJwt.service;

//import jakarta.security.auth.message.AuthException;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.strebkov.T1_SpringSecurityJwt.domain.dto.JwtAuthenticationTokenResponse;
import ru.strebkov.T1_SpringSecurityJwt.domain.dto.SignInRequest;
import ru.strebkov.T1_SpringSecurityJwt.domain.dto.SignUpRequest;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.Role;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.User;
import ru.strebkov.T1_SpringSecurityJwt.exception.AuthException;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtServiceAccessToken jwtServiceAccessToken;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtServiceRefreshToken jwtServiceRefreshToken;

    /**
     * Или использовать какое-нибудь постоянное хранилище
     */
    private final Map<String, String> refreshStorage = new HashMap<>();


    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationTokenResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);
        var accessToken = jwtServiceAccessToken.generateToken(user);
        var refreshToken = jwtServiceRefreshToken.generateTokenR(user);
        refreshStorage.put(user.getUsername(), refreshToken);

        return new JwtAuthenticationTokenResponse(accessToken, refreshToken);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationTokenResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());
        var accessToken = jwtServiceAccessToken.generateToken(user);
        var refreshToken = jwtServiceRefreshToken.generateTokenR(user);
        refreshStorage.put(user.getUsername(), refreshToken);

        return new JwtAuthenticationTokenResponse(accessToken, refreshToken);
    }

    /**
     * Если рефреш-токен валиден, то получаем новый access-токен
     *
     * @param refreshToken
     * @return
     */
    public JwtAuthenticationTokenResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtServiceRefreshToken.isTokenValidR(refreshToken)) {
            final String username = jwtServiceRefreshToken.extractUserNameR(refreshToken);
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByUsername(username);
                final String accessToken = jwtServiceAccessToken.generateToken(user);

                return new JwtAuthenticationTokenResponse(accessToken, null);
            }
        }
        return new JwtAuthenticationTokenResponse(null, null);
    }

    /**
     * Если рефреш-токен валиден, то получаем новый access-токен и рефреш-токен
     * Например: Refresh токен выдается на 30 дней. Примерно на 25-29 день клиент
     * API отправляет валидный refresh токен вместе с валидным access токеном и
     * взамен получает новую пару токенов.
     *
     * @param refreshToken
     * @return
     */
    public JwtAuthenticationTokenResponse refresh(@NonNull String refreshToken) {
        if (jwtServiceRefreshToken.isTokenValidR(refreshToken)) {
            final String username = jwtServiceRefreshToken.extractUserNameR(refreshToken);
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByUsername(username);
                final String accessToken = jwtServiceAccessToken.generateToken(user);
                final String newRefreshToken = jwtServiceRefreshToken.generateTokenR(user);

                refreshStorage.put(user.getUsername(), newRefreshToken);

                return new JwtAuthenticationTokenResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

}

