package com.kata.cinema.base.service.entity;

public interface ScoreService {

    void createScore(Long movieId, Integer score, Long userId);

    void updateScore(Long movieId, Long scoreId, Integer score);

    void deleteScore(Long movieId, Long scoreId);
}
