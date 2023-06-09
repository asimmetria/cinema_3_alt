package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class GenreResponseDto {
    private Long id;
    private String name;

    public GenreResponseDto() {
    }

    public GenreResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
