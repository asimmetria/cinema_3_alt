package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Excertion;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExcertionService {
    List<Excertion> findAllExcertions();

    List<Excertion> getMovieExcertion(Long id, Pageable pageable);

    List<Excertion> getPersonExcertion(Long id, Pageable pageable);

    Excertion getExcertion(Long id);

    void save(Excertion excertion);

    void deleteById(Long id);

    boolean isExist(Long id);
}
