package com.reactive.cashout.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.reactive.cashout.model.Cashout;
import com.reactive.cashout.repository.CashoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

public class CashoutServiceTest {

    @Mock
    private CashoutRepository cashoutRepository;

    @Mock
    private UserService userService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private TransactionHistoryService transactionHistoryService;

    @InjectMocks
    private CashoutService cashoutService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetCashoutsByUserId() {
        String userId = "123";

        Cashout cashout1 = new Cashout();
        cashout1.setUserId(userId);
        cashout1.setAmount(50.0);

        Cashout cashout2 = new Cashout();
        cashout2.setUserId(userId);
        cashout2.setAmount(30.0);

        when(cashoutRepository.findByUserId(userId)).thenReturn(Flux.just(cashout1, cashout2));

        Flux<Cashout> result = cashoutService.getCashoutsByUserId(userId);

        assertEquals(2, result.collectList().block().size());
    }
}
