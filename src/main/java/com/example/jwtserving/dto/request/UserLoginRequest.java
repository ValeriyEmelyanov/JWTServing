package com.example.jwtserving.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserLoginRequest {

    private final String login;

    private final String password;

}
