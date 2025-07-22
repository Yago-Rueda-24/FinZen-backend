package com.YagoRueda.backend.controlles;

import com.YagoRueda.backend.Dtos.UserDto;
import com.YagoRueda.backend.exceptions.InvalidInputDataException;
import com.YagoRueda.backend.models.UserEntity;
import com.YagoRueda.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto dto) throws InvalidInputDataException {

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
}
