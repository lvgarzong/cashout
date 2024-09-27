package com.reactive.cashout.model;

import lombok.Getter;
import lombok.Setter;


@Getter
public class PaymentRequest {
    private final String userId;
    private final Double amount;

    public PaymentRequest(String userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

}