package ru.strebkov.T1_SpringSecurityJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.RefreshToken;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.User;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
