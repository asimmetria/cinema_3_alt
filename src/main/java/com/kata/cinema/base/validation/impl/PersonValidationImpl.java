package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.PersonRepository;
import com.kata.cinema.base.validation.PersonValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonValidationImpl implements PersonValidation {

    private final PersonRepository personRepository;

    @Override
    public void isExistPersonById(Long id) {
        log.debug("Start validation is exist person with id = {}", id);

        if (!personRepository.existsById(id)) {
            log.error("Person with id = {} does not exist", id);
            throw new NotFoundEntityException(String.format("Персона с таким id = %s не существует", id));
        }

        log.info("Success validation is exist person with id = {}", id);
    }

}
