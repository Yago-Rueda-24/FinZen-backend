package com.YagoRueda.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;
    @Getter
    @Setter
    @Column(unique = true)
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Instant signup_date;


}
