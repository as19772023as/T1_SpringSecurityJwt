package ru.strebkov.T1_SpringSecurityJwt.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class JwtResponse {

    private Long id;
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String username;
    private String email;
    private Role role;


}
