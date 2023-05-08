package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.MovieViewResponseDto;

public interface MovieViewService {
    MovieViewResponseDto getMovieViewResponseDto(Long id);
}
