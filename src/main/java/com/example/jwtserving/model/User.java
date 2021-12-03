package com.example.jwtserving.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@ToString(of = {"login", "enabled", "roles"})
@EqualsAndHashCode(of = {"login"})
public class User {

    private long id;

    private String login;

    @JsonIgnore
    private String password;

    private boolean enabled;

    private LocalDateTime created;

    private LocalDateTime modified;

    private Set<Role> roles;

}
