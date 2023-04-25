package com.kata.cinema.base.webapp.facade.user.impl;

import com.kata.cinema.base.service.entity.ScoreService;
import com.kata.cinema.base.validation.ScoreValidation;
import com.kata.cinema.base.webapp.facade.user.ScoreServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ScoreServiceFacadeImpl implements ScoreServiceFacade {

    private final ScoreService scoreService;
    private final ScoreValidation scoreValidation;

    @Override
    public void createScore(Long movieId, Integer score, Long userId) {
        scoreService.createScore(movieId, score, userId);
    }

    @Override
    public void updateScore(Long movieId, Long scoreId, Integer score) {
        scoreService.updateScore(movieId, scoreId, score);
    }

    @Override
    public void deleteScore(Long movieId, Long scoreId) {
        scoreValidation.isExistScoreById(scoreId);
        scoreService.deleteScore(movieId, scoreId);
    }
}
