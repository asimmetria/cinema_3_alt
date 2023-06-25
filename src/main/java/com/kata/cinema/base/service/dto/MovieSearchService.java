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
import java.util.Map;

public interface MovieSearchService {
    Page<SearchMovieResponseDto> searchMovies(
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

    Page<SearchMovieResponseDto> getMoviesByAuthors(Map<Long, List<Long>> parameters, Integer pageNumber, Integer itemsOnPage);
}
