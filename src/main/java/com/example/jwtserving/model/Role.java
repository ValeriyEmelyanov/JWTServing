package com.example.jwtserving.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jwtserving.model.Permission.ADMIN_READ;
import static com.example.jwtserving.model.Permission.ADMIN_WRITE;
import static com.example.jwtserving.model.Permission.USER_READ;
import static com.example.jwtserving.model.Permission.USER_WRITE;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Role {
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE)),
    SUPERVISOR(Set.of(ADMIN_READ, USER_READ)),
    USER(Set.of(USER_READ, USER_WRITE)),
    GUEST(Set.of(USER_READ));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .distinct()
                .map(e -> new SimpleGrantedAuthority(e.getPermission()))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return name();
    }
}
