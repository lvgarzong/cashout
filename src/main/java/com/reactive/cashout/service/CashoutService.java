package com.reactive.cashout.service;

import com.reactive.cashout.model.Cashout;
import com.reactive.cashout.repository.CashoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CashoutService {

    @Autowired
    private CashoutRepository cashoutRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService; // Servicio de pago interno

    @Autowired
    private TransactionHistoryService transactionHistoryService; // Servicio de historial de transacciones

    public Mono<Cashout> createCashout(String userId, Double amount) {
        return userService.getUserById(userId)
                .flatMap(user -> {
                    if (user.getBalance() >= amount) {
                        return paymentService.processPayment(userId, amount)
                                .flatMap(payment -> {
                                    if ("approved".equals(payment.getStatus())) {
                                        return userService.updateUserBalance(userId, -amount)
                                                .flatMap(updatedUser -> {
                                                    Cashout cashout = new Cashout();
                                                    cashout.setUserId(userId);
                                                    cashout.setAmount(amount);
                                                    return cashoutRepository.save(cashout)
                                                            .flatMap(savedCashout -> {
                                                                // Guardar en el historial de transacciones
                                                                return transactionHistoryService.createTransactionHistory(
                                                                        userId, amount, "cashout", savedCashout.getId()
                                                                ).thenReturn(savedCashout);
                                                            });
                                                });
                                    } else {
                                        return Mono.error(new RuntimeException("Payment failed"));
                                    }
                                });
                    } else {
                        return Mono.error(new RuntimeException("Balance insuficiente"));
                    }
                });
    }

    public Flux<Cashout> getCashoutsByUserId(String userId) {
        return cashoutRepository.findByUserId(userId);
    }
}
