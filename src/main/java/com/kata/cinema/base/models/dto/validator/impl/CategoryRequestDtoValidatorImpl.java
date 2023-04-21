package com.kata.cinema.base.models.dto.validator.impl;

import com.kata.cinema.base.models.dto.request.CategoryRequestDto;
import com.kata.cinema.base.models.dto.validator.CategoryRequestDtoValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CategoryRequestDtoValidatorImpl implements CategoryRequestDtoValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryRequestDto categoryRequestDto = (CategoryRequestDto) target;
        if (categoryRequestDto.getName() == null || categoryRequestDto.getName().isEmpty()) {
            errors.rejectValue("name", "category.name.empty", "Name cannot be empty");
        }
        if (categoryRequestDto.getName() != null && categoryRequestDto.getName().length() > 50) {
            errors.rejectValue("name", "category.name.length", "Name is too long");
        }
    }
}