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
    public void saveCountryByName(String name) {
        Country country = new Country();
        country.setName(name);
        movieCountryRepository.save(country);
    }

    @Override
    public void update(Long id, String name) {
        Country country = movieCountryRepository.getCountryById(id);
        country.setName(name);
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

    @Override
    public boolean isExistCountryByName(String name) {
        return movieCountryRepository.existsCountryByName(name);
    }

    @Override
    public boolean isExistCountryById(Long id) {
        return movieCountryRepository.existsCountryById(id);
    }

    @Override
    public boolean existsCountryByMovieId(Long id) {
        return movieCountryRepository.existsCountryByMovieId(id);
    }

}
