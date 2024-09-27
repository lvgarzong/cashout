package com.reactive.cashout.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.reactive.cashout.model.Payment;
import com.reactive.cashout.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessPayment_Success() {
        String userId = "123";
        Double amount = 50.0;

        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setStatus("approved");

        when(paymentRepository.save(any(Payment.class))).thenReturn(Mono.just(payment));

        Mono<Payment> result = paymentService.processPayment(userId, amount);

        Payment paymentResult = result.block();
        assertNotNull(paymentResult);
        assertEquals("approved", paymentResult.getStatus());
        assertEquals(userId, paymentResult.getUserId());
    }
}

