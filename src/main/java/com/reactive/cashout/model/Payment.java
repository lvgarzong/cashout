package com.reactive.cashout.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    @Setter
    private String userId;
    @Setter
    private Double amount;
    @Setter
    private String status;
    @Setter
    private String transactionId;


}

