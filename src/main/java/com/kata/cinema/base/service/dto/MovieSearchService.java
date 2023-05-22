package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

public interface MovieSearchService {
    public Page<SearchMovieResponseDto> searchMovies(
            Integer pageNumber,
            Integer itemsOnPage,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            List<String> genres,
            RARS rars,
            MPAA mpaa,
            MovieSortType sortType
    );

}
