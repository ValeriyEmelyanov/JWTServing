package com.example.jwtserving.service.impl;

import com.example.jwtserving.exception.WrongLoginPasswordException;
import com.example.jwtserving.model.User;
import com.example.jwtserving.security.JwtProvider;
import com.example.jwtserving.service.AuthService;
import com.example.jwtserving.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserService userService, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @NonNull
    public String auth(@NonNull String login, @NonNull String password) {
        Assert.notNull(login, "Login should not be null");
        Assert.notNull(password, "Password should not be null");

        User user;
        try {
            user = userService.getByLogin(login);
        } catch (Exception e) {
            log.info("User with login {} not found", login);
            throw new WrongLoginPasswordException("Wrong login or password");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.info("Wrong password for user with login {}", login);
            throw new WrongLoginPasswordException("Wrong login or password");
        }

        log.info("Generated the token for {}", login);
        return jwtProvider.generateToken(login, user.getAuthorities());
    }

}
