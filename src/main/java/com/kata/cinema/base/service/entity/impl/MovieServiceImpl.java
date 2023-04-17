package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.service.entity.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return movieRepository.existsMovieById(id);
    }
}
