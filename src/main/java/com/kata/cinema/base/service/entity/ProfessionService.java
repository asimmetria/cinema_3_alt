package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.entitys.Profession;
import java.util.List;

public interface ProfessionService {
    void save (Profession profession);

    void deleteById(Long id);

    ProfessionResponseDto createProfession(String name);

    ProfessionResponseDto updateProfession(Long id, String name);

    List<ProfessionResponseDto> getAllProfessions();
}
