package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class ProfessionResponseDto {
    private Long id;
    private String name;

    public ProfessionResponseDto() {
    }

    public ProfessionResponseDto (Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
