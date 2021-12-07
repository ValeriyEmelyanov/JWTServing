package com.example.jwtserving.controller;

import com.example.jwtserving.model.PermissionNames;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {
    private static final String PERMISSION_USER_READ = "hasAuthority('" + PermissionNames.USER_READ + "')";
    private static final String PERMISSION_USER_WRITE = "hasAuthority('" + PermissionNames.USER_WRITE + "')";

    @PreAuthorize(PERMISSION_USER_READ)
    @GetMapping
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Info from the User resource");
    }

    @PreAuthorize(PERMISSION_USER_WRITE)
    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.ok("Post operation on the User resource");
    }
}
