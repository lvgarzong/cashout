package com.reactive.cashout.controller;

import com.reactive.cashout.model.Cashout;
import com.reactive.cashout.model.TransactionHistory;
import com.reactive.cashout.repository.CashoutRepository;
import com.reactive.cashout.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/transaction-history")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    // Obtener historial de transacciones por ID de usuario
    @GetMapping("/user/{userId}")
    public Flux<TransactionHistory> getTransactionHistoryByUserId(@PathVariable String userId) {
        return transactionHistoryService.getTransactionHistoryByUserId(userId);
    }
}
