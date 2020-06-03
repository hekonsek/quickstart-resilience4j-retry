package com.github.hekonsek.quickstart.resilience4j.retry.model;

public class User {

    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String username() {
        return username;
    }

}