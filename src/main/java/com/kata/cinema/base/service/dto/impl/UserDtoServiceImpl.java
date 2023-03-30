package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.service.dto.UserDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDtoServiceImpl implements UserDtoService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }
}
