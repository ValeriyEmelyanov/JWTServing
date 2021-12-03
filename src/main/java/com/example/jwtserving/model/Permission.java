package com.example.jwtserving.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Permission {
    ADMIN_READ(PermissionNames.ADMIN_READ),
    ADMIN_WRITE(PermissionNames.ADMIN_WRITE),
    USER_READ(PermissionNames.USER_READ),
    USER_WRITE(PermissionNames.USER_WRITE);

    private final String permission;
}
