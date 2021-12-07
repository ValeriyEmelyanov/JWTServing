package com.example.jwtserving.controller;

import com.example.jwtserving.model.PermissionNames;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminResource {
    private static final String PERMISSION_ADMIN_READ = "hasAuthority('" + PermissionNames.ADMIN_READ + "')";
    private static final String PERMISSION_ADMIN_WRITE = "hasAuthority('" + PermissionNames.ADMIN_WRITE + "')";

    @PreAuthorize(PERMISSION_ADMIN_READ)
    @GetMapping
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Info from the Admin resource");
    }

    @PreAuthorize(PERMISSION_ADMIN_WRITE)
    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.ok("Post operation on the Admin resource");
    }

}
