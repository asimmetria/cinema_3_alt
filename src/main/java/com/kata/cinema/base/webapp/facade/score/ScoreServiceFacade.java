package com.kata.cinema.base.webapp.facade.score;

public interface ScoreServiceFacade {

    void createScore(Long movieId, Integer score, Long userId);

    void updateScore(Long movieId, Long scoreId, Integer score);

    void deleteScore(Long movieId, Long scoreId);
}
