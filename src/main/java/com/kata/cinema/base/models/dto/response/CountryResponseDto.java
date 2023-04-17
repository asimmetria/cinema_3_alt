package com.kata.cinema.base.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryResponseDto {

    private Long id;

    private String name;

    public CountryResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
