package com.reactive.cashout.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "transaction_history")
public class TransactionHistory {

    @Id
    private String id;
    private String userId;
    private Double amount;
    private String transactionType;
    private String transactionId;

}
