package org.andarworld.authenticationservice.usecases;

import org.andarworld.authenticationservice.usecases.dto.UserRequestDto;
import org.andarworld.authenticationservice.usecases.dto.UserResponseDto;

public interface UserService {
    UserResponseDto save(UserRequestDto userRequestDto);
}
