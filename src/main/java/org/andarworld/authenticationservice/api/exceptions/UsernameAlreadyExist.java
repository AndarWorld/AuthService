package org.andarworld.authenticationservice.api.exceptions;

public class UsernameAlreadyExist extends RuntimeException {

    public UsernameAlreadyExist(String message) {
        super(message);
    }
}
