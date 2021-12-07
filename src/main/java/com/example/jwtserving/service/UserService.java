package com.example.jwtserving.service;

import com.example.jwtserving.model.User;

public interface UserService {

    User getByLogin(String login);

    User save(User user);
}
