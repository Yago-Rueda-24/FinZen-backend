package com.YagoRueda.backend.services;

import com.YagoRueda.backend.models.TransactionEntity;
import com.YagoRueda.backend.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {


    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Devuelve una lista con todas las transacciones de un usuario
     * @return Lista de transacciones
     */
    public List<TransactionEntity> getAll() {
        return transactionRepository.findAll();
    }

    /**
     * Añade una transacción a la lista de transacciones de un usuario
     * @param entity la entidad a añadir
     */
    public TransactionEntity addTransaction(TransactionEntity entity){
        return transactionRepository.save(entity);

    }
}
