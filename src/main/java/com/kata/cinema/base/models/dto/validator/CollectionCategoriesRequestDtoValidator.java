package com.kata.cinema.base.models.dto.validator;

import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import jakarta.validation.ValidationException;

public interface CollectionCategoriesRequestDtoValidator {
    boolean supports(Class<?> clazz);

    void validate(CollectionCategoriesRequestDto collectionCategoriesRequestDto) throws ValidationException;
}

