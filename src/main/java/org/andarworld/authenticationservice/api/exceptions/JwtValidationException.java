package org.andarworld.authenticationservice.api.exceptions;

public class JwtValidationException extends RuntimeException {

    public JwtValidationException(String message) {
        super(message);
    }
}
