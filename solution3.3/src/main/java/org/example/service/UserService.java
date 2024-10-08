package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> getAllUsers();

    void deleteById(Long id);

    User findById(Long id);

    void update(User user);
}
