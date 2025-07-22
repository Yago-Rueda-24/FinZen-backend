package com.YagoRueda.backend.services;

import com.YagoRueda.backend.models.TokenEntity;
import com.YagoRueda.backend.models.UserEntity;
import com.YagoRueda.backend.repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final Duration TOKEN_DURATION = Duration.ofHours(2);

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public TokenEntity createToken(UserEntity user) {
        TokenEntity token = new TokenEntity();
        token.setTokenId(UUID.randomUUID().toString());
        token.setUser(user);
        token.setCreatedAt(Instant.now());
        token.setExpiresAt(Instant.now().plus(30, ChronoUnit.MINUTES));
        return tokenRepository.save(token);
    }

    // Borrar un token específico
    public void deleteToken(String tokenId) {
        tokenRepository.findByTokenId(tokenId).ifPresent(tokenRepository::delete);
    }

    // Borrar todos los tokens de un usuario (por logout, por ejemplo)
    public void deleteAllTokensForUser(UserEntity user) {
        tokenRepository.deleteByUser(user);
    }

    // Validar y renovar token si aún no ha caducado
    public boolean validateAndRenewToken(String tokenId) {
        Optional<TokenEntity> optionalToken = tokenRepository.findByTokenId(tokenId);
        if (optionalToken.isPresent()) {
            TokenEntity token = optionalToken.get();
            if (token.getExpiresAt().isAfter(Instant.now())) {
                token.setExpiresAt(Instant.now().plus(30, ChronoUnit.MINUTES)); // Renovación
                tokenRepository.save(token);
                return true;
            }
        }
        return false;
    }

}
