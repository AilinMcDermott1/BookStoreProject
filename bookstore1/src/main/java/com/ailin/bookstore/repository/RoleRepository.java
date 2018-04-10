package com.ailin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ailin.bookstore.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
