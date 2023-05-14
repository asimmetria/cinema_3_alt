package com.kata.cinema.base.my;

import com.kata.cinema.base.models.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieSortType;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.dto.impl.MovieSearchServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/search/movies")
public class MovieSearchController {

    private final MovieSearchServiceImpl movieSearchService;

    public MovieSearchController(MovieSearchServiceImpl movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @GetMapping("/page/{pageNumber}")
    public Page<SearchMovieResponseDto> searchMovies(
            @PathVariable Integer pageNumber,
            @RequestParam Integer itemsOnPage,
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
}



