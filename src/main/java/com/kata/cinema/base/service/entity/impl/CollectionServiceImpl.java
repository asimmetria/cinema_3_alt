package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.dto.response.CollectionMoviesResponseDto;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.CollectionMovie;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.enums.CollectionSortType;
import com.kata.cinema.base.repository.CollectionMovieRepository;
import com.kata.cinema.base.repository.CollectionRepository;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.MoviePaginationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final MovieServiceImpl movieService;
    private final MoviePaginationService moviePaginationService;

    private final CollectionMovieRepository collectionMovieRepository;
    private final CollectionRepository collectionRepository;

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
    public CollectionMoviesResponseDto getCollectionMovie(Long id, Long countryId, Long genreId, LocalDate date, CollectionSortType collectionSortType, int pageNumber, Long size) {

        Page<MovieResponseDto> pageMovieResponseDto = moviePaginationService.getPageMovieResponse(id, date, countryId, genreId, pageNumber, size, collectionSortType);

        CollectionMoviesResponseDto collectionMoviesResponseDto = collectionRepository.getCollectionDtoById(id);
        collectionMoviesResponseDto.setMovies(pageMovieResponseDto);

        return collectionMoviesResponseDto;
    }
}
