package com.github.hekonsek.quickstart.resilience4j.retry.service;

import com.github.hekonsek.quickstart.resilience4j.retry.model.User;

public interface UserService {

    String saveUser(User user);

}
