package com.kata.cinema.base.webapp.sheduled;

import com.kata.cinema.base.service.entity.impl.TopMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TopMovieUpdate {

    private final TopMovieService topMovieService;

    @Autowired
    public TopMovieUpdate(TopMovieService topMovieService) {
        this.topMovieService = topMovieService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateTopMovies() {
        topMovieService.updateTopMovies();
    }
}
