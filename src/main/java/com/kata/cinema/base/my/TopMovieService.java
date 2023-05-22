package com.kata.cinema.base.my;

import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TopMovieService {
    @Autowired
    private TopMovieRepository topMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateTopMovies() {
        List<TopMovie> topMovieList = new ArrayList<>();

        movieRepository.findAll().forEach(movie -> {
            double rating = calculateRating(movie);
            if (movie.getNumRatings() > 5000) {
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
        List<Rating> ratings = movie.getRatings();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Rating rating : ratings) {
            sum += rating.getScore();
        }

        double avgRating = sum / ratings.size();
        double numRatings = (double) ratings.size();
        double bayesianRating = (numRatings / (numRatings + 50.0)) * avgRating + (50.0 / (numRatings + 50.0)) * 3.0;
        return bayesianRating;
    }
}






