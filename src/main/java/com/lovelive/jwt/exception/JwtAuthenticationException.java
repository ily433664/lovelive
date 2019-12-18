package com.lovelive.jwt.exception;

public class JwtAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = -8631354432884749019L;

    public JwtAuthenticationException(String message) {
        super(message);
    }

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtAuthenticationException(Throwable cause) {
        super(cause);
    }
}

