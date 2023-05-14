package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.my.MovieSpecification;
import com.kata.cinema.base.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSearchServiceImpl {

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
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsOnPage);

        Page<Movie> moviePage = movieRepository.findAll((Specification<Movie>) MovieSpecification.searchMovies(pageNumber,itemsOnPage,name, startDate, endDate, genres, rars, mpaa,sortType), pageable);

        List<SearchMovieResponseDto> dtos = moviePage.getContent().stream()
                .map(movie -> {
                    SearchMovieResponseDto dto = new SearchMovieResponseDto();
                    dto.setId(movie.getId());
                    dto.setName(movie.getName());
                    dto.setOriginalName(movie.getOriginalName());
                    dto.setDateRelease(movie.getDateRelease());
                    dto.setPreviewUrl(movie.getPreviewUrl());
                    dto.setGenres(Collections.singletonList(movie.getGenres()));
                    // Set other properties as needed
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, moviePage.getTotalElements());
    }
}
