package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.enums.CollectionSortType;
import com.kata.cinema.base.repository.GenreRepository;
import com.kata.cinema.base.repository.MovieCountryRepository;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.entity.MoviePaginationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
@AllArgsConstructor
public class MoviePaginationServiceImpl implements MoviePaginationService {

    private final GenreRepository genreRepository;
    private final MovieCountryRepository countryRepository;
    private final MovieRepository movieRepository;


    @Override
    public Page<MovieResponseDto> getPageMovieResponse(Long id, LocalDate date, Long countryId, Long genreId,  int page, Long size, CollectionSortType collectionSortType) {
        Pageable pageable = PageRequest.of(page, Math.toIntExact(size), collectionSortType.getSortType());
        List<MovieResponseDto> movies = movieRepository.getMoviesResponseByCollection(id, date, countryId, genreId);

        List<List<String>> genres = movies.stream().map(m -> m.getId()).map( idMovie -> genreRepository.getGenreByMovieId(idMovie)).toList();
        List<List<String>> countries =  movies.stream().map(m -> m.getId()).map( idMovie -> countryRepository.getCountriesNameByMovieId(idMovie)).toList();

        int i = 0;
        for (MovieResponseDto dto: movies) {
            dto.setGenres(genres.get(i));
            dto.setCountries(countries.get(i));
            i++;
        }
        Collections.sort(movies, getSortComparator(collectionSortType));


        Page<MovieResponseDto> movieResponsePage = new PageImpl<>(movies, pageable, movies.size());

        return movieResponsePage;
    }

    private Comparator<MovieResponseDto> getSortComparator(CollectionSortType sortType) {
        switch (sortType) {
            case COUNT_SCORE:
                return (o1, o2) -> o1.getCountScore().compareTo(o2.getCountScore());
            case RATING:
                return (o1, o2) -> o1.getAvgScore().compareTo(o2.getAvgScore());
            case RELEASE_DATE:
                return (o1, o2) -> o1.getDateRelease().compareTo(o2.getDateRelease());
            case NAME:
                return ((o1, o2) -> o1.getName().compareTo(o2.getName()));
            default:
                return (o1, o2) -> o1.getId().compareTo(o2.getId());
        }
    }
}
