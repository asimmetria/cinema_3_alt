package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.CollectionMovie;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.repository.CollectionMovieRepository;
import com.kata.cinema.base.repository.CollectionRepository;
import com.kata.cinema.base.service.entity.CollectionService;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    private final MovieServiceImpl movieService;

    private final CollectionMovieRepository collectionMovieRepository;

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
}
