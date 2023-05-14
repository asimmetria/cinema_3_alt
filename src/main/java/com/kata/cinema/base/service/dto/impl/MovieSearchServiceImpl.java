package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        Pageable pageable = PageRequest.of(pageNumber, itemsOnPage, getSort(sortType));
        Page<Movie> moviesPage = movieRepository.searchMovies(name, startDate, endDate, genres, rars, mpaa, pageable);
        return moviesPage.map(this::toSearchMovieResponseDto);
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
                movie.getOriginName(),
                movie.getDateRelease(),
                movie.getPreviewUrl(),
                movie.getGenre()
        );
    }
}
