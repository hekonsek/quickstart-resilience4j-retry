package com.github.hekonsek.quickstart.resilience4j.retry;

import com.github.hekonsek.quickstart.resilience4j.retry.model.User;
import com.github.hekonsek.quickstart.resilience4j.retry.repository.CrappyUserRepository;
import com.github.hekonsek.quickstart.resilience4j.retry.service.DefaultUserService;
import com.github.hekonsek.quickstart.resilience4j.retry.service.UserService;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import static java.time.Duration.ofMillis;

public class UsersApplication {

    public static void main(String[] args) {
        Retry userRepositoryRetry = Retry.of("userRepository",
                RetryConfig.custom().maxAttempts(5).waitDuration(ofMillis(500)).build()
        );
        UserService userService = new DefaultUserService(new CrappyUserRepository(), userRepositoryRetry);

        String userId = userService.saveUser(new User("Henryk"));
        System.out.println("User saved with id: " + userId);
    }

}