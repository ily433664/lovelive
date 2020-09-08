package com.lovelive.security.exception;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 7500424935709624740L;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}

