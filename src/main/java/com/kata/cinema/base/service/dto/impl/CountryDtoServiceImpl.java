package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.CountryResponseDto;
import com.kata.cinema.base.repository.MovieCountryRepository;
import com.kata.cinema.base.service.dto.CountryDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryDtoServiceImpl implements CountryDtoService {

    private final MovieCountryRepository movieCountryRepository;

    @Override
    public List<CountryResponseDto> getCountriesByMovieId(Long id) {
        return movieCountryRepository.getCountriesByMovieId(id);
    }
}
