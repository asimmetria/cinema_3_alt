package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.entitys.Genre;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.dto.MovieSearchService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSearchServiceImpl implements MovieSearchService {

    private final MovieRepository movieRepository;

    public MovieSearchServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

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
    ) {
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsOnPage, getSort(sortType));
        Page<Movie> moviesPage = movieRepository.searchMovies(name, startDate, endDate, genres, rars, mpaa, pageable);
        List<SearchMovieResponseDto> dtos = moviesPage
                .getContent()
                .stream()
                .map(this::toSearchMovieResponseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, moviesPage.getTotalElements());
    }

    private Sort getSort(MovieSortType sortType) {
        if (sortType == null) {
            return Sort.by(Sort.Direction.DESC, "dateRelease");
        }
        switch (sortType) {
            case DATE_RELEASE_ASC:
                return Sort.by(Sort.Direction.ASC, "dateRelease");
            case DATE_RELEASE_DESC:
                return Sort.by(Sort.Direction.DESC, "dateRelease");
            case NAME_ASC:
                return Sort.by(Sort.Direction.ASC, "name");
            case NAME_DESC:
                return Sort.by(Sort.Direction.DESC, "name");
            default:
                throw new IllegalArgumentException("Unsupported sort type: " + sortType);
        }
    }

    private SearchMovieResponseDto toSearchMovieResponseDto(Movie movie) {
        return new SearchMovieResponseDto(
                movie.getId(),
                movie.getName(),
                movie.getOriginalName(),
                movie.getDateRelease(),
                movie.getPreviewUrl(),
                Collections.singletonList(movie.getGenres())
        );
    }
}