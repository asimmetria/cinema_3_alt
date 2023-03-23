package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Country;

public interface CountryService {

    void save(Country country);

    void update(Country country);

    void deleteById(Long id);

    Country getProxyById(Long id);

    Country getCountryById(Long id);
}
