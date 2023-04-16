package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.NotFoundEntityException;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.entitys.Score;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.repository.MovieRepository;
import com.kata.cinema.base.repository.ScoreRepository;
import com.kata.cinema.base.repository.UserRepository;
import com.kata.cinema.base.service.entity.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;


    @Override
    public void createScore(Long movieId, Integer score, Long userId) {
        if (movieRepository.existsById(movieId)) {
            Score newScore = new Score();
            Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundEntityException("Фильм не существует"));
            User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundEntityException("Пользователь не существует"));
            newScore.setMovie(movie);
            newScore.setUser(user);
            newScore.setScore(score);
            scoreRepository.save(newScore);
        }
    }

    @Override
    public void updateScore(Long movieId, Long scoreId, Integer score) {
        if (movieRepository.existsById(movieId) && scoreRepository.existsById(scoreId)) {
            Score scoreToUpdate = scoreRepository.getScoreById(scoreId);
            Movie givenMovie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new NotFoundEntityException("Фильм не существует"));
            // Проверка: изменяемая оценка относится к передаваемому фильму?
            if (scoreToUpdate.getMovie().equals(givenMovie)) {
                scoreToUpdate.setScore(score);
                scoreRepository.save(scoreToUpdate);
            }
        }
    }

    @Override
    public void deleteScore(Long movieId, Long scoreId) {
        Score scoreToDelete = scoreRepository.getScoreById(scoreId);
        Movie givenMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundEntityException("Фильм не существует"));
        // Проверка: удаляемая оценка относится к передаваемому фильму?
        if (scoreToDelete.getMovie().equals(givenMovie)) {
            scoreRepository.deleteById(scoreId);
        }
    }
}
