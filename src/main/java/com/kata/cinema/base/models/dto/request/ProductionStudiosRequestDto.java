package com.kata.cinema.base.models.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductionStudiosRequestDto {
    private LocalDate dateFoundation;

    private String name;

    private String description;
}
