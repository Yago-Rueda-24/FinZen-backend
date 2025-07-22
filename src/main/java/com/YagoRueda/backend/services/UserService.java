package com.YagoRueda.backend.services;

import com.YagoRueda.backend.Dtos.UserDto;
import com.YagoRueda.backend.exceptions.InvalidInputDataException;
import com.YagoRueda.backend.models.UserEntity;
import com.YagoRueda.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Service
public class UserService {

    private MessageDigest hash;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        try {
            this.hash = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String hashString(String prehash) {
        hash.reset();
        hash.update(prehash.getBytes());
        byte[] hashed_password = this.hash.digest();
        return new String(hashed_password, StandardCharsets.UTF_8);
    }

    public UserEntity signup(UserDto dto) throws InvalidInputDataException {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new InvalidInputDataException("El nombre de usuario ya existe, utiliza otro nombre porfavor.");
        }
        if (!dto.getPassword().equals(dto.getPasswordRepeat())) {
            throw new InvalidInputDataException("las contraseñas no coinciden");
        }
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(hashString(dto.getPassword()));
        entity.setSignup_date(Instant.now());
        return userRepository.save(entity);
    }
}
