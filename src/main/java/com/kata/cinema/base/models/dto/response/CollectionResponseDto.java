package com.kata.cinema.base.models.dto.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CollectionResponseDto {

    private Long id;

    private String name;

    private String collectionUrl;

    private Long countMovies;

    public CollectionResponseDto(Long id, String name, String collectionUrl, Long countMovies) {
        this.id = id;
        this.name = name;
        this.collectionUrl = collectionUrl;
        this.countMovies = countMovies;
    }
}
