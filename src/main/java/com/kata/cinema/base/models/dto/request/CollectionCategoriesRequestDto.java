package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.dto.validator.CollectionCategoriesRequestDtoValidator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionCategoriesRequestDto {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    public void validate(CollectionCategoriesRequestDtoValidator validator) {
        validator.validate(this);
    }
}