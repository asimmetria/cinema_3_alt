package com.kata.cinema.base.models.dto.validator;

import org.springframework.validation.Errors;

public interface CategoryRequestDtoValidator {

    boolean supports(Class<?> clazz);

    void validate(Object target, Errors errors);

}