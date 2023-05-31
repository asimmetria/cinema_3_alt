package com.kata.cinema.base.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCollectionDto {

    String name;
    String url;
    Integer countMovies;


}