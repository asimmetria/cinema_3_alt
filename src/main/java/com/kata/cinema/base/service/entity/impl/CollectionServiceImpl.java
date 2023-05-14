package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.Movie;
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
        collectionRepository.deactivateById(id, Byte.valueOf("0"));
    }

    @Transactional
    @Override
    public void activateCollection(Long id) {
        collectionRepository.activateById(id, Byte.valueOf("1"));
    }

    @Transactional
    @Override
    @Access(value = AccessType.FIELD)
    public void addMovieToCollection(Collection collection, List<Long> movieIds) throws MovieNotFoundException {


        List<Movie> movies = new ArrayList<>();
        if (movieIds != null && !movieIds.isEmpty()) {
            movies = movieService.getMoviesByIds(movieIds);
            if (movies.size() != movieIds.size()) {
                throw new MovieNotFoundException("Some movies were not found");
            }
        }

        collection.getMovies().addAll(movies);
        collectionRepository.save(collection);
    }

    @Transactional
    @Override
    @Access(value = AccessType.FIELD)
    public void deleteMovieFromCollection(Collection collection, List<Long> movieIds) throws MovieNotFoundException {

        List<Movie> movies = new ArrayList<>();
        if (movieIds != null && !movieIds.isEmpty()) {
            movies = movieService.getMoviesByIds(movieIds);
            if (movies.size() != movieIds.size()) {
                throw new MovieNotFoundException("Some movies were not found");
            }
        }

        movies.forEach(collection.getMovies()::remove);
        collectionRepository.save(collection);
    }
}
