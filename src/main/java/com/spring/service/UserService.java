package com.spring.service;

import com.spring.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(int userId);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

    User getUserByLogin(String login);
}
