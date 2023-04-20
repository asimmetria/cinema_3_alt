package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Country;

import java.util.List;
import java.util.Set;

public interface CountryService {

    void save(Country country);

    void saveCountryByName(String name);

    void update(Long id, String name);

    void deleteById(Long id);

    Country getProxyById(Long id);

    Country getCountryById(Long id);

    Set<Country> getCountriesByIds(List<Long> ids);

    boolean isExistCountryByName(String name);

    boolean isExistCountryById(Long id);

    boolean existsCountryByMovieId(Long id);

}
