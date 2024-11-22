package org.andarworld.authenticationservice.usecases.impl;

import lombok.RequiredArgsConstructor;
import org.andarworld.authenticationservice.persistence.model.User;
import org.andarworld.authenticationservice.persistence.repository.UserRepository;
import org.andarworld.authenticationservice.security.UserDetailsImpl;
import org.andarworld.authenticationservice.security.util.JwtUtil;
import org.andarworld.authenticationservice.usecases.UserService;
import org.andarworld.authenticationservice.usecases.dto.UserRequestDto;
import org.andarworld.authenticationservice.usecases.dto.UserResponseDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.email(), passwordEncoder.encode(userRequestDto.password()));
        User persistedUser = userRepository.save(user);
        String jwtToken = jwtUtil.generateToken(new UserDetailsImpl(persistedUser));
        return new UserResponseDto(persistedUser.getEmail(), jwtToken);
    }

    @Override
    public UserResponseDto login(UserRequestDto userRequestDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userRequestDto.email());
        if (!passwordEncoder.matches(userRequestDto.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password!");
        }
        String token = jwtUtil.generateToken(userDetails);
        return new UserResponseDto(userDetails.getUsername(), token);
    }

    @Override
    public void isJwtValid(String token) {
        jwtUtil.validateToken(token);
    }

    @Override
    public boolean isUserExist(String email) {
       return userRepository.existsByEmail(email);
    }

}
