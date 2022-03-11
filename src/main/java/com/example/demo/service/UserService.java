package com.example.demo.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.demo.model.User;

public interface UserService {
    CompletableFuture<List<User>>  getUsers();
    User saveUser(User user);
}
