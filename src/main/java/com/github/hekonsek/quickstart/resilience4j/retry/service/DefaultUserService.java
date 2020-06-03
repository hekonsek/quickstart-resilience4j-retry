package com.github.hekonsek.quickstart.resilience4j.retry.service;

import com.github.hekonsek.quickstart.resilience4j.retry.model.User;
import com.github.hekonsek.quickstart.resilience4j.retry.repository.UserRepository;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;

import java.util.function.Supplier;

public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final Retry userRepositoryRetry;

    public DefaultUserService(UserRepository userRepository, Retry userRepositoryRetry) {
        this.userRepository = userRepository;
        this.userRepositoryRetry = userRepositoryRetry;
    }

    public String saveUser(User user) {
        Supplier<String> action = () -> userRepository.saveUser(user);
        action = Retry.decorateSupplier(userRepositoryRetry, action);

        String id = Try.ofSupplier(action).recover(throwable -> "").get();
        if (id.isBlank()) {
            throw new RuntimeException("Cannot save user.");
        }
        return id;
    }

}