package com.reactive.cashout.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class PaymentControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testProcessPayment() {
        webTestClient.post()
                .uri("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"userId\": \"123\", \"amount\": 50.0}")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("approved");
    }
}
