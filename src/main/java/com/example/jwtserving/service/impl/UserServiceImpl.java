package com.example.jwtserving.service.impl;

import com.example.jwtserving.exception.LoginAlreadyTakenException;
import com.example.jwtserving.model.User;
import com.example.jwtserving.repository.UserRepository;
import com.example.jwtserving.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public User getByLogin(@NonNull String login) {
        Assert.notNull(login, "Login should not be null");

        log.info("Requested the User with login: {}", login);
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    @Override
    @NonNull
    public User save(User user) {
        Optional<User> byLogin = userRepository.findByLogin(user.getLogin());
        if (byLogin.isPresent()) {
            throw new LoginAlreadyTakenException("The login is already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
