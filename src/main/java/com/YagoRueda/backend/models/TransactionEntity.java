package com.YagoRueda.backend.models;

import com.YagoRueda.backend.enums.TransactionTypes;
import com.YagoRueda.backend.exceptions.InvalidInputDataException;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double amount = 0;
    private Instant date;
    private TransactionTypes type;

    public TransactionEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionTypes getType() {
        return type;
    }

    public void setType(TransactionTypes type) {
        this.type = type;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) throws InvalidInputDataException {
        if (amount <= 0) {
            throw new InvalidInputDataException("No se pueden introducir un importe negativo");
        }
        this.amount = amount;
    }
}
