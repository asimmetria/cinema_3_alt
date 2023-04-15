package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class CategoryResponseDto {

    Long id;
    String name;

    public CategoryResponseDto() {
    }

    public CategoryResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
