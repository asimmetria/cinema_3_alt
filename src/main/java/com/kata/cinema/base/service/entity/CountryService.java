package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Country;

public interface CountryService {

    void save(Country country);

    void saveCountryByName(String name);

    void update(Long id, String name);

    void deleteById(Long id);

    Country getProxyById(Long id);

    Country getCountryById(Long id);

    boolean isExistCountryByName(String name);

    boolean isExistCountryById(Long id);

    boolean existsCountryByMovieId(Long id);

}
