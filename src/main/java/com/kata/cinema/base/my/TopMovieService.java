package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.entitys.Score;
import com.kata.cinema.base.models.entitys.TopMovie;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.repository.ScoreRepository;
import com.kata.cinema.base.repository.TopMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TopMovieService {
    @Autowired
    private TopMovieRepository topMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScoreRepository scoreRepository;


    public void updateTopMovies() {
        List<TopMovie> topMovieList = new ArrayList<>();

        movieRepository.findAll().forEach(movie -> {
            double rating = calculateRating(movie);
            if (getNumRatings(movie) > 5000) {
                TopMovie topMovie = new TopMovie();
                topMovie.setMovie(movie);
                topMovie.setPositionRating(0);
                topMovieList.add(topMovie);
            }
        });

        topMovieRepository.deleteAll();
        topMovieRepository.saveAll(topMovieList);
    }

    private double calculateRating(Movie movie) {
        List<Score> scores = scoreRepository.findByMovie(movie);

        if (scores.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Score score : scores) {
            sum += score.getScore();
        }

        double avgRating = sum / scores.size();
        double numRatings = (double) scores.size();
        double bayesianRating = (numRatings / (numRatings + 50.0)) * avgRating + (50.0 / (numRatings + 50.0)) * 3.0;
        return bayesianRating;
    }

    private int getNumRatings(Movie movie) {
        return scoreRepository.countByMovie(movie);
    }
}




