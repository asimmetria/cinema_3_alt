package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.CollectionSortType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MoviePaginationService {
    Page<MovieResponseDto> getPageMovieResponse(List<Movie> movies, int page, int size, CollectionSortType collectionSortType);
}
