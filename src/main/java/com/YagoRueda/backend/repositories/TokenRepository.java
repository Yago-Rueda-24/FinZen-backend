package com.YagoRueda.backend.repositories;

import com.YagoRueda.backend.models.TokenEntity;
import com.YagoRueda.backend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByTokenId(String tokenId);

    List<TokenEntity> findByUser(UserEntity user);

    void deleteByTokenId(String tokenId);

    void deleteAllByExpiresAtBefore(Instant now);

    void deleteByUser(UserEntity user);

    @Query("SELECT t FROM TokenEntity t WHERE t.user = :user AND t.expired = false AND t.revoked = false")
    List<TokenEntity> getLiveTokens(UserEntity user);
}
