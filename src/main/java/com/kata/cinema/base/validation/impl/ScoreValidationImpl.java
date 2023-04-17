package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.ScoreRepository;
import com.kata.cinema.base.validation.ScoreValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
@Slf4j
public class ScoreValidationImpl implements ScoreValidation {

    private final ScoreRepository scoreRepository;

    @Override
    public void isExistScoreById(Long id) {
        log.debug("Start validation is exist score with id = {}", id);

        if (!scoreRepository.existsById(id)) {
            log.error("Score with id = {} does not exist", id);
            throw new NotFoundEntityException(String.format("Оценка с таким id = %s не существует", id));
        }

        log.info("Success validation is exist score with id = {}", id);
    }
}
