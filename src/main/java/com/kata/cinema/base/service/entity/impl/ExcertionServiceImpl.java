package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Excertion;
import com.kata.cinema.base.repository.ExcertionRepository;
import com.kata.cinema.base.service.entity.ExcertionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExcertionServiceImpl implements ExcertionService {
    private final ExcertionRepository excertionRepository;

    @Override
    public List<Excertion> findAllExcertions() {
        return excertionRepository.findAll();
    }

    @Override
    public List<Excertion> getMovieExcertion(Long id, Pageable pageable) {
        return excertionRepository.getExcertionsByMovieId(id, pageable);
    }

    @Override
    public List<Excertion> getPersonExcertion(Long id, Pageable pageable) {
        return excertionRepository.getExcertionsByPersonId(id, pageable);
    }

    @Override
    public Excertion getExcertion(Long id) {
        return excertionRepository.getExcertionById(id);
    }

    @Override
    public void save(Excertion excertion) {
        excertionRepository.save(excertion);
    }

    @Override
    public void deleteById(Long id) {
        excertionRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return excertionRepository.existsExcertionById(id);
    }
}
