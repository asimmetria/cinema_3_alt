package com.kata.cinema.base.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRequestDto {

    private String name;

    private String description;

    private Boolean enable;
}
