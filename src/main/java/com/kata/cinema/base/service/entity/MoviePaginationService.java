package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.enums.CollectionSortType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface MoviePaginationService {


    Page<MovieResponseDto> getPageMovieResponse(Long id, LocalDate date, Long countryId, Long genreId,
                                                int page, Long size, CollectionSortType collectionSortType);

}
