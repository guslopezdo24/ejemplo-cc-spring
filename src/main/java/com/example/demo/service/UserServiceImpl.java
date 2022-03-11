package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Override
    @CircuitBreaker(name = "dbback", fallbackMethod = "fallback" )
    @TimeLimiter(name = "dbback")
    public CompletableFuture<List<User>> getUsers() {
        return CompletableFuture.supplyAsync(() -> {
            return this.userRepo.findAll();
        });
    }


    private CompletableFuture<List<User>> fallback(Exception ex) {
        log.error("en el fallback: " + ex.getClass());
        return CompletableFuture.supplyAsync(() -> {
            return new ArrayList<>();
        });
        
    }



    @Override
    public User saveUser(User user) {
        
        return this.userRepo.save(user);
    }
    
}
