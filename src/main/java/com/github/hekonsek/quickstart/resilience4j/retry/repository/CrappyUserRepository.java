package com.github.hekonsek.quickstart.resilience4j.retry.repository;

import com.github.hekonsek.quickstart.resilience4j.retry.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class CrappyUserRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public String saveUser(User user) {
        boolean successfulSave = new Random().nextBoolean();
        if (!successfulSave) {
            System.out.println("My database connection is crappy...");
            throw new RuntimeException("I cannot connect to a database :( ");
        }
        String id = UUID.randomUUID().toString();
        users.put(UUID.randomUUID().toString(), user);
        return id;
    }

}