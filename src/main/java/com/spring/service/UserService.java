package com.spring.service;

import com.spring.model.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    void addUser(User application);

    void deleteUser(int userId);

    void updateUser(User application);

    List<User> getAllUsers();

    User getUserById(int userId);

    User getUserByLogin(String login);

    ResponseEntity<ArrayList> getAllUsers1();

    ResponseEntity<User> getUserById1(int userId);
}
