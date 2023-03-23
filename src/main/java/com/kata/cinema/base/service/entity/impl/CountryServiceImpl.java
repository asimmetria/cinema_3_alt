package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Country;
import com.kata.cinema.base.repository.MovieCountryRepository;
import com.kata.cinema.base.service.entity.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final MovieCountryRepository movieCountryRepository;

    @Override
    public void save(Country country) {
        movieCountryRepository.save(country);
    }

    @Override
    public void update(Country country) {
        movieCountryRepository.save(country);
    }

    @Override
    public void deleteById(Long id) {
        movieCountryRepository.deleteById(id);
    }

    @Override
    public Country getProxyById(Long id) {
        return movieCountryRepository.getReferenceById(id);
    }

    @Override
    public Country getCountryById(Long id) {
        return movieCountryRepository.getCountryById(id);
    }
}
