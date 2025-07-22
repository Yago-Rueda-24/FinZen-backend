package com.YagoRueda.backend.repositories;

import com.YagoRueda.backend.models.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
