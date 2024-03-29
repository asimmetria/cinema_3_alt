package com.kata.cinema.base.webapp.rest;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.dto.MovieSearchService;
import com.kata.cinema.base.service.dto.impl.MovieSearchServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MovieSearchController {

    private final MovieSearchServiceImpl movieSearchService;

    public MovieSearchController(MovieSearchServiceImpl movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @GetMapping("/api/search/movies/page/{pageNumber}")
    public Page<SearchMovieResponseDto> searchMovies(
            @PathVariable Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer itemsOnPage,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) RARS rars,
            @RequestParam(required = false) MPAA mpaa,
            @RequestParam(required = false) MovieSortType sortType
    ) {
        return movieSearchService.searchMovies(pageNumber, itemsOnPage, name, startDate, endDate, genres, rars, mpaa, sortType);
    }

    @PostMapping("/api/search/movies/page/{pageNumber}")
    public Page<SearchMovieResponseDto> getMoviesByAuthors(
            @PathVariable Integer pageNumber,
            @RequestParam(defaultValue = "10", required = false) Integer itemsOnPage,
            @RequestBody Map<Long, List<Long>> parameters
    ) {
        return movieSearchService.getMoviesByAuthors(parameters, pageNumber, itemsOnPage);
    }

}




