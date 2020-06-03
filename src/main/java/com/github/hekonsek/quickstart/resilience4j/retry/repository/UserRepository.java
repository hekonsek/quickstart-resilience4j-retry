package com.github.hekonsek.quickstart.resilience4j.retry.repository;

import com.github.hekonsek.quickstart.resilience4j.retry.model.User;

public interface UserRepository {

    String saveUser(User user);

}
