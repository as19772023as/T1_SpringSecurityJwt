package ru.strebkov.T1_SpringSecurityJwt.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}
