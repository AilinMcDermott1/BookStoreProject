package com.ailin.bookstore.service;

import com.ailin.bookstore.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
