package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.validation.MovieValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieValidationImpl implements MovieValidation {

    private final MovieRepository movieRepository;

    @Override
    public void isExistMovieById(Long id) {
        log.debug("Start validation is exist folder with id = {}", id);

        if (!movieRepository.existsById(id)) {
            log.error("Movie with id = {} does not exist", id);
            throw new NotFoundEntityException(String.format("Фильм с таким id = %s не существует", id));
        }

        log.info("Success validation is exist movie with id = {}", id);
    }
}
