package com.mylabs.service;

import com.mylabs.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
