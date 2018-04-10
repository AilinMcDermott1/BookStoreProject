package com.ailin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ailin.bookstore.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
