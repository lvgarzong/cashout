package com.reactive.cashout.repository;

import com.reactive.cashout.model.TransactionHistory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionHistoryRepository extends ReactiveMongoRepository<TransactionHistory, String> {
    Flux<TransactionHistory> findByUserId(String userId);
}
