package com.kata.cinema.base.models.dto.validator.impl;

import com.kata.cinema.base.models.dto.request.CategoryRequestDto;
import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import com.kata.cinema.base.models.dto.validator.CollectionCategoriesRequestDtoValidator;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CollectionCategoriesDtoValidatorImpl implements CollectionCategoriesRequestDtoValidator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CollectionCategoriesRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(CollectionCategoriesRequestDto CollectionCategoriesRequestDto) throws ValidationException {
        if (CollectionCategoriesRequestDto.getName() == null || CollectionCategoriesRequestDto.getName().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (CollectionCategoriesRequestDto.getName().length() > 50) {
            throw new ValidationException("Name is too long");
        }
    }
}
