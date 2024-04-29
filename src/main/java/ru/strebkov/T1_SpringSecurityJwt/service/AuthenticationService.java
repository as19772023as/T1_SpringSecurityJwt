package ru.strebkov.T1_SpringSecurityJwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.strebkov.T1_SpringSecurityJwt.domain.dto.JwtAuthenticationTokenResponse;
import ru.strebkov.T1_SpringSecurityJwt.domain.dto.SignInRequest;
import ru.strebkov.T1_SpringSecurityJwt.domain.dto.SignUpRequest;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.Role;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.User;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //TODO ++++++++++++++++++++++++++++++++++++++++
    private  final RefreshTokenService refreshTokenService;

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

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationTokenResponse(jwt);
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

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationTokenResponse(jwt);
    }
}
