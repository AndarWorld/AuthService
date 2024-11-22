package org.andarworld.authenticationservice.usecases.dto;

import org.andarworld.authenticationservice.usecases.annotation.IsUserExistConstraint;

public record UserRequestDto(
        @IsUserExistConstraint
        String email,

        String password) {
}
