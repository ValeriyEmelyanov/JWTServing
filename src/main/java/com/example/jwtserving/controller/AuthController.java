package com.example.jwtserving.controller;

import com.example.jwtserving.dto.request.UserLoginRequest;
import com.example.jwtserving.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.auth(request.getLogin(), request.getPassword()));
    }
}
