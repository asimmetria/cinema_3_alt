package com.kata.cinema.base.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionMoviesResponseDto {
    private Long id;
    private String collectionName;
    private String description;
    private String collectionUrl;
    private Page<MovieResponseDto> movies;
}
