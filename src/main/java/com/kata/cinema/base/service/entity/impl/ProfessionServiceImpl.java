package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.entitys.Profession;
import com.kata.cinema.base.repository.CastRepository;
import com.kata.cinema.base.repository.ProfessionRepository;
import com.kata.cinema.base.service.entity.ProfessionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
class ProfessionServiceImpl implements ProfessionService {
    private final ProfessionRepository professionRepository;

    private final CastRepository castRepository;

    @Override
    public void save(Profession profession) {
        professionRepository.save(profession);
    }

    @Override
    public void deleteById(Long id) {
        if (castRepository.existsByProfessionId(id)) {
            throw new RuntimeException("Cannot delete profession because it is linked to a cast");
        }
        professionRepository.deleteById(id);
    }

    @Override
    public void createProfession(String name) {
        Profession profession = new Profession();
        profession.setName(name);
        professionRepository.save(profession);
    }

    @Override
    public void updateProfession(Long id, String name) {
        Optional<Profession> professionOpt = professionRepository.findById(id);
        if (professionOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profession not found");
        }
        Profession profession = professionOpt.get();
        profession.setName(name);
        professionRepository.save(profession);
    }

    @Override
    public List<ProfessionResponseDto> getAllProfessions() {
        return professionRepository.findAll().stream()
            .map(profession -> {
                ProfessionResponseDto dto = new ProfessionResponseDto();
                dto.setId(profession.getId());
                dto.setName(profession.getName());
                return dto;
            })
            .collect(Collectors.toList());
    }
}
