package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class ExcertionResponseDto {
    private Long id;
    private String description;

    public ExcertionResponseDto() {
    }

    public ExcertionResponseDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
