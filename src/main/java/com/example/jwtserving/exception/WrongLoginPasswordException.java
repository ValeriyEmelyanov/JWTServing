package com.example.jwtserving.exception;

public class WrongLoginPasswordException extends RuntimeException {
    public WrongLoginPasswordException(String message) {
        super(message);
    }
}
