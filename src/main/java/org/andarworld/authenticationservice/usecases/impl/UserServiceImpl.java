package org.andarworld.authenticationservice.usecases.impl;

import lombok.RequiredArgsConstructor;
import org.andarworld.authenticationservice.persistence.model.User;
import org.andarworld.authenticationservice.persistence.repository.UserRepository;
import org.andarworld.authenticationservice.usecases.UserService;
import org.andarworld.authenticationservice.usecases.dto.UserRequestDto;
import org.andarworld.authenticationservice.usecases.dto.UserResponseDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Target;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.email(), passwordEncoder.encode(userRequestDto.password()));
        User persistedUser = userRepository.save(user);
        return new UserResponseDto(persistedUser.getEmail());
    }
}
