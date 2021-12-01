package com.example.jwtserving.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    @GetMapping
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Info from the User resource");
    }

}
