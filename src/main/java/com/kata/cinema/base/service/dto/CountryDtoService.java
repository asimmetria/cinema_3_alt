package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.CountryResponseDto;

import java.util.List;

public interface CountryDtoService {

    List<CountryResponseDto> getCountriesByMovieId(Long id);
}
