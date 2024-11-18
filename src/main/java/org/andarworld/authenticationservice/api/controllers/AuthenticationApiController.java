package org.andarworld.authenticationservice.api.controllers;

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
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationApiController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
        log.debug("Login Request: {}", userRequestDto);
        UserResponseDto userResponseDto = userService.save(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserRequestDto userRequestDto) {
        log.debug("Login Request: {}", userRequestDto);
        UserResponseDto userResponseDto = userService.save(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
