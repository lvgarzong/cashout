package com.reactive.cashout.controller;

import com.reactive.cashout.model.TransactionHistory;
import com.reactive.cashout.repository.TransactionHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class TransactionHistoryControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @BeforeEach
    public void setUp() {
        TransactionHistory history = new TransactionHistory();
        history.setUserId("123");
        history.setAmount(50.0);
        history.setTransactionType("cashout");
        history.setTransactionId("tx123");

        transactionHistoryRepository.save(history).block();  // Save a test record
    }

    @Test
    public void testGetTransactionHistoryByUserId() {
        webTestClient.get()
                .uri("/transaction-history/user/{userId}", "123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].userId").isEqualTo("123");
    }
}
