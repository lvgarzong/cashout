package com.reactive.cashout.controller;

import com.reactive.cashout.model.Cashout;
import com.reactive.cashout.repository.CashoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class CashoutControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CashoutRepository cashoutRepository;

    @BeforeEach
    public void setUp() {
        Cashout cashout = new Cashout();
        cashout.setUserId("123");
        cashout.setAmount(50.0);

        cashoutRepository.save(cashout).block();  // Ensure the cashout record is saved for testing
    }

    @Test
    public void testGetCashoutsByUserId() {
        webTestClient.get()
                .uri("/cashouts/user/{userId}", "123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].userId").isEqualTo("123");  // Checking the first item in the response array
    }
}
