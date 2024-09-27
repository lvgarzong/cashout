package com.reactive.cashout.service;

import com.reactive.cashout.model.User;
import com.reactive.cashout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener usuario por ID
    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Crear usuario
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    // Actualizar balance
    public Mono<User> updateUserBalance(String userId, Double amount) {
        return userRepository.findById(userId)
                .flatMap(user -> {
                    user.setBalance(user.getBalance() + amount);
                    return userRepository.save(user);
                });
    }
}