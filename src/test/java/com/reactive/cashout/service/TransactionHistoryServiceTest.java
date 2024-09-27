package com.reactive.cashout.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.reactive.cashout.model.TransactionHistory;
import com.reactive.cashout.repository.TransactionHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class TransactionHistoryServiceTest {

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @InjectMocks
    private TransactionHistoryService transactionHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransactionHistory_Success() {
        String userId = "123";
        Double amount = 50.0;
        String transactionType = "cashout";
        String transactionId = "tx123";

        TransactionHistory history = new TransactionHistory();
        history.setUserId(userId);
        history.setAmount(amount);
        history.setTransactionType(transactionType);
        history.setTransactionId(transactionId);

        when(transactionHistoryRepository.save(any(TransactionHistory.class))).thenReturn(Mono.just(history));

        Mono<TransactionHistory> result = transactionHistoryService.createTransactionHistory(userId, amount, transactionType, transactionId);

        TransactionHistory savedHistory = result.block();
        assertNotNull(savedHistory);
        assertEquals(userId, savedHistory.getUserId());
        assertEquals(transactionType, savedHistory.getTransactionType());
        assertEquals(transactionId, savedHistory.getTransactionId());
    }

    @Test
    public void testGetTransactionHistoryByUserId_Success() {
        String userId = "123";

        TransactionHistory history1 = new TransactionHistory();
        history1.setUserId(userId);
        history1.setAmount(50.0);

        TransactionHistory history2 = new TransactionHistory();
        history2.setUserId(userId);
        history2.setAmount(30.0);

        when(transactionHistoryRepository.findByUserId(userId)).thenReturn(Flux.just(history1, history2));

        Flux<TransactionHistory> result = transactionHistoryService.getTransactionHistoryByUserId(userId);

        assertNotNull(result);
        assertEquals(2, Objects.requireNonNull(result.collectList().block()).size());
    }

    @Test
    public void testGetTransactionHistoryByUserId_NoHistory() {
        String userId = "123";

        when(transactionHistoryRepository.findByUserId(userId)).thenReturn(Flux.empty());

        Flux<TransactionHistory> result = transactionHistoryService.getTransactionHistoryByUserId(userId);

        assertNotNull(result);
        assertEquals(0, Objects.requireNonNull(result.collectList().block()).size());
    }
}
