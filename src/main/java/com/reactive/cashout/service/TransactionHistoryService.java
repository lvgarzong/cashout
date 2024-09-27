package com.reactive.cashout.service;

import com.reactive.cashout.model.TransactionHistory;
import com.reactive.cashout.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public Mono<TransactionHistory> createTransactionHistory(String userId, Double amount, String transactionType, String transactionId) {
        TransactionHistory history = new TransactionHistory();
        history.setUserId(userId);
        history.setAmount(amount);
        history.setTransactionType(transactionType);
        history.setTransactionId(transactionId);
        return transactionHistoryRepository.save(history);
    }

    public Flux<TransactionHistory> getTransactionHistoryByUserId(String userId) {
        return transactionHistoryRepository.findByUserId(userId);
    }
}
