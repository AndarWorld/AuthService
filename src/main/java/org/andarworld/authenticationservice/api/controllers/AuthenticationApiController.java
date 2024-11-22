package org.andarworld.authenticationservice.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andarworld.authenticationservice.usecases.UserService;
import org.andarworld.authenticationservice.usecases.dto.UserRequestDto;
import org.andarworld.authenticationservice.usecases.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationApiController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        log.debug("Sign up request: {}", userRequestDto);
        UserResponseDto userResponseDto = userService.save(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserRequestDto userRequestDto) {
        log.debug("Sign in request: {}", userRequestDto);
        UserResponseDto userResponseDto = userService.login(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/validate")
    public ResponseEntity<Void> validateJwt(@RequestBody String jwt) {
        log.debug("Validate JWT: {}", jwt);
        userService.isJwtValid(jwt);
        return ResponseEntity.ok().build();
    }
}
