package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Profession;
import com.kata.cinema.base.repository.ProfessionRepository;
import com.kata.cinema.base.service.entity.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ProfessionServiceImpl implements ProfessionService {
    private final ProfessionRepository professionRepository;
    @Override
    public void save(Profession profession) {
        professionRepository.save(profession);
    }

    @Override
    public void deleteById(Long id) {
        professionRepository.deleteById(id);
    }
}
