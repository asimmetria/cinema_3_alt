package com.kata.cinema.base.models.dto.response;

import lombok.Data;

@Data
public class ProfessionResponseDto {
    public Long id;
    public String name;

    public ProfessionResponseDto() {
    }

    public ProfessionResponseDto (Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
