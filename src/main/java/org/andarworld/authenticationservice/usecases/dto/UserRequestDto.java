package org.andarworld.authenticationservice.usecases.dto;

public record UserRequestDto(
        String email,

        String password) {
}
