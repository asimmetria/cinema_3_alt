package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.UserResponseDto;

public interface UserDtoService {
    UserResponseDto getUserById(Long userId);
}
