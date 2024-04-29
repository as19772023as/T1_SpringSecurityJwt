package ru.strebkov.T1_SpringSecurityJwt.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest {

    @Schema(description = "RefreshToken")
    @NotBlank
    private String refreshToken;

}
