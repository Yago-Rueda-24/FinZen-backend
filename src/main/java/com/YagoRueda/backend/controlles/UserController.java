package com.YagoRueda.backend.controlles;

import com.YagoRueda.backend.Dtos.UserDto;
import com.YagoRueda.backend.exceptions.InvalidInputDataException;
import com.YagoRueda.backend.exceptions.UnauthorizedException;
import com.YagoRueda.backend.models.UserEntity;
import com.YagoRueda.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto dto) {

        if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno o varios campos estan vacios");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno o varios campos estan vacios");
        }
        if (dto.getPasswordRepeat() == null || dto.getPasswordRepeat().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno o varios campos estan vacios");
        }
        try {
            UserEntity entity = userService.signup(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        } catch (InvalidInputDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto dto) {
        if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno o varios campos estan vacios");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno o varios campos estan vacios");
        }
        try {
            String token = userService.login(dto);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (InvalidInputDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String token) {
        try {
            UserEntity user = userService.logout(token);
            return ResponseEntity.status(HttpStatus.OK).body(user.toDTO());
        } catch (InvalidInputDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
