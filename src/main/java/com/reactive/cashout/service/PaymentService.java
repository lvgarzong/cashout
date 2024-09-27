package com.reactive.cashout.service;

import com.reactive.cashout.model.Payment;
import com.reactive.cashout.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Procesar pago (interno)
    public Mono<Payment> processPayment(String userId, Double amount) {
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setStatus("approved"); // Simulamos que siempre es aprobado
        payment.setTransactionId(UUID.randomUUID().toString()); // Generamos un ID único para la transacción

        return paymentRepository.save(payment); // Guardar el pago y retornarlo
    }
}
