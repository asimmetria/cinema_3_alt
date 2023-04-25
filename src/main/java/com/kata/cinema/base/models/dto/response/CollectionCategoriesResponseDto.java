package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.CollectionCategories;
import lombok.Data;

@Data
public class CollectionCategoriesResponseDto {
    private Long id;
    private String name;

    public CollectionCategoriesResponseDto() {
    }

    public CollectionCategoriesResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CollectionCategoriesResponseDto(CollectionCategories category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
