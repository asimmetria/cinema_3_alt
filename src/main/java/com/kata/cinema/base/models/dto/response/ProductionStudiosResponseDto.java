package com.kata.cinema.base.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionStudiosResponseDto {

    private String dateFoundation;

    private String name;

    private String description;
}
