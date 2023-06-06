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

    public CollectionMoviesResponseDto(Long id,
                                       String collectionName,
                                       String description,
                                       String collectionUrl) {
        this.id = id;
        this.collectionName = collectionName;
        this.description = description;
        this.collectionUrl = collectionUrl;
    }
}
