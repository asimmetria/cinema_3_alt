package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Profession;

public interface ProfessionService {
    void save (Profession profession);

    void deleteById(Long id);
}
