package com.YagoRueda.backend.controlles;

import com.YagoRueda.backend.models.TransactionEntity;
import com.YagoRueda.backend.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<?> getall() {
        List<TransactionEntity> transactions = transactionService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransactionEntity transaction){
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

}
