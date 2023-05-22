package com.kata.cinema.base.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TopMovieUpdateService {

    private final TopMovieService topMovieService;

    @Autowired
    public TopMovieUpdateService(TopMovieService topMovieService) {
        this.topMovieService = topMovieService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateTopMovies() {
        topMovieService.updateTopMovies();
    }
}
