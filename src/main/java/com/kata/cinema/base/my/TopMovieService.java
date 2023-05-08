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
        List<Movie> movies = movieRepository.findAll();

        Map<Movie, Double> ratings = new HashMap<>();
        for (Movie movie : movies) {
            double rating = calculateRating(movie);
            ratings.put(movie, rating);
        }

        List<Map.Entry<Movie, Double>> topMovies = ratings.entrySet()
                .stream()
                .filter(e -> e.getKey().getNumRatings() > 5000)
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(250)
                .collect(Collectors.toList());

        List<TopMovie> topMovieList = new ArrayList<>();
        int position = 1;
        for (Map.Entry<Movie, Double> entry : topMovies) {
            TopMovie topMovie = new TopMovie();
            topMovie.setMovie(entry.getKey());
            topMovie.setPositionRating(position++);
            topMovieList.add(topMovie);
        }

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


