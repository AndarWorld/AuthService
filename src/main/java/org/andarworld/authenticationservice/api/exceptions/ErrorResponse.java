package org.andarworld.authenticationservice.api.exceptions;

public record ErrorResponse(
        String message,
        Integer statusCode,
        String date
) {
}
