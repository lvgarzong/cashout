package com.reactive.cashout.controller;
import com.reactive.cashout.model.Payment;
import com.reactive.cashout.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Mono<Payment> processPayment(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");
        Double amount = (Double) request.get("amount");
        return paymentService.processPayment(userId, amount);
    }
}
