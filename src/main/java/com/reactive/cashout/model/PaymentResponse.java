package com.reactive.cashout.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PaymentResponse {
    private final String userId;
    private final Double amount;
    @Setter
    private String paymentStatus;

    public PaymentResponse(String userId, Double amount, String paymentStatus) {
        this.userId = userId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

}