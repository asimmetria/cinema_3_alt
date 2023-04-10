package com.kata.cinema.base.validation.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserValidationImpl implements UserValidation {

    private final UserRepository userRepository;

    @Override
    public void isExistUserById(long id) {
        log.debug("Start validation is exist user with id = {}", id);

        if (!userRepository.existsById(id)) {
            log.error("User with id = {} is not exist", id);
            throw new NotFoundEntityException(String.format("Пользователь с таким id = %s не существует", id));
        }

        log.info("Success validation is exist user with id = {}", id);
    }
}
