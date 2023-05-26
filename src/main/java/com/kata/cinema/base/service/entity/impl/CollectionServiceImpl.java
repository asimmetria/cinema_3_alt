package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.converter.collection.CollectionMovieMapper;
import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.dto.response.CollectionMoviesResponseDto;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entitys.*;
import com.kata.cinema.base.models.enums.CollectionSortType;
import com.kata.cinema.base.repository.CollectionMovieRepository;
import com.kata.cinema.base.repository.CollectionRepository;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.entity.*;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    private final MovieServiceImpl movieService;
    private final CountryService countryService;
    private final GenreService genreService;

    private final CollectionMovieRepository collectionMovieRepository;
    private final MoviePaginationService moviePaginationService;


    @Override
    public void save(Collection collection) {
        collectionRepository.save(collection);
    }

    @Override
    public void deleteById(Long id) {
        collectionRepository.deleteById(id);
    }

    @Override
    public Collection getById(Long id) {
        return collectionRepository.getCollectionById(id);
    }

    @Transactional
    @Override
    public void deactivateCollection(Long id) {
        collectionRepository.deactivateById(id, false);
    }

    @Transactional
    @Override
    public void activateCollection(Long id) {
        collectionRepository.activateById(id, true);
    }

    @Transactional
    @Override
    public void addMovieToCollection(Collection collection, List<Long> movieIds) throws MovieNotFoundException {

        if (movieIds != null && !movieIds.isEmpty()) {
            List<Movie> movies = movieService.getMoviesByIds(movieIds);
            if (movies.size() != movieIds.size()) {
                throw new MovieNotFoundException("Some movies were not found");
            }
            List<CollectionMovie> collectionMovies = new ArrayList<>();
            for (Movie movie : movies) {
                CollectionMovie collectionMovie = new CollectionMovie();
                collectionMovie.setCollection(collection);
                collectionMovie.setMovie(movie);
                collectionMovies.add(collectionMovie);
            }
            collectionMovieRepository.saveAll(collectionMovies);
        }
    }

    @Transactional
    @Override
    public void deleteMovieFromCollection(Collection collection, List<Long> movieIds) throws MovieNotFoundException {

        if (movieIds != null && !movieIds.isEmpty()) {
            List<Movie> movies = movieService.getMoviesByIds(movieIds);
            if (movies.size() != movieIds.size()) {
                throw new MovieNotFoundException("Some movies were not found");
            }
            List<CollectionMovie> collectionMovies = collectionMovieRepository.findByCollectionAndMovieIn(collection, movies);
            collectionMovieRepository.deleteAll(collectionMovies);
        }
    }

    @Transactional
    @Override
    public CollectionMoviesResponseDto getCollectionMovie(Long id, Long countryId, Long genreId, LocalDate date, CollectionSortType collectionSortType) {

        // получаем коллекцию
        Collection collection = collectionRepository.getCollectionById(id);

        // Получаем список с фильмами из этой коллекции
        List<Movie> movieList = collectionMovieRepository.getCollectionMovieById(id, date).stream().map(cm -> cm.getMovie()).toList();

        // есть ли в запросе условие поиска фильмов по Странам и/или Жанрам
        if (countryId != null) {
            Country country = countryService.getCountryById(countryId);
            movieList = movieList.stream().filter(m -> m.getCountry().contains(country)).toList();

        }
        if (genreId != null) {
            Genre genre = genreService.getGenre(genreId);
            movieList = movieList.stream().filter(m -> m.getGenre().contains(genre)).toList();
        }

        //Получаем страницу с видеоОтветами, передавая лист с фильмами
        Page<MovieResponseDto> pageMovieResponseDto = moviePaginationService.getPageMovieResponse(movieList, 0, 100, collectionSortType);

        //Создаем ответ
        CollectionMoviesResponseDto collectionMoviesResponseDto =
                new CollectionMoviesResponseDto(
                        collection.getId(),
                        collection.getName(),
                        collection.getDescription(),
                        collection.getCollectionUrl(),
                        // вкладываем новосозданную страницу
                        pageMovieResponseDto
                );

        return collectionMoviesResponseDto;
    }
}
