package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.CollectionCategories;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    private Long id;
    private String name;

    public CategoryResponseDto(CollectionCategories category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}