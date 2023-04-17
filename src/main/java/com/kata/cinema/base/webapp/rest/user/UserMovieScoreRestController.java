package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.webapp.facade.score.ScoreServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping( "/api/user/movies")
@RequiredArgsConstructor
public class UserMovieScoreRestController {

    private final ScoreServiceFacade scoreServiceFacade;

    @PostMapping("/{id}/score")
    public ResponseEntity<Void> createMovieScore(@PathVariable("id") Long id,
                                                 @RequestParam("score") Integer score,
                                                 @RequestParam("userId") Long userId) {
        scoreServiceFacade.createScore(id, score, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/score/{scoreId}")
    public ResponseEntity<Void> updateMovieScore(@PathVariable("id") Long id,
                                                 @PathVariable("scoreId") Long scoreId,
                                                 @RequestParam("score") Integer score) {
        scoreServiceFacade.updateScore(id, scoreId, score);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/score/{scoreId}")
    public ResponseEntity<Void> deleteMovieScore(@PathVariable("id") Long id,
                                                 @PathVariable("scoreId") Long scoreId) {
        scoreServiceFacade.deleteScore(id, scoreId);
        return ResponseEntity.ok().build();
    }
}
