package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.entitys.CollectionCategories;

public class CategoryResponseDto {

    private Long id;
    private String name;

    public CategoryResponseDto(CollectionCategories category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}