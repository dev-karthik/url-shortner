package com.mylabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mylabs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
