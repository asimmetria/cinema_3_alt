package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.webapp.facade.unauthorized.ReviewServiceFacade;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;


import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserReviewRestController {
    private final ReviewServiceFacade reviewServiceFacade;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewTitleResponseDto>> getReview(@RequestParam(value = "isModerate", required = false) String isModerate) {
        return ResponseEntity.ok(reviewServiceFacade.getListReviews(isModerate));
    }

    @PostMapping("movies/{movieId}")
    public ResponseEntity<Void> saveNewReview(@PathVariable Long movieId,
                                              @RequestBody ReviewRequestDto reviewRequestDto,
                                              @RequestParam("userId") Long userId) {
        reviewServiceFacade.createReview(movieId, userId, reviewRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable Long reviewId,
                                             @RequestBody ReviewRequestDto reviewRequestDto) {
        if (!reviewServiceFacade.isExistReview(reviewId)) {
            return ResponseEntity.notFound().build();
        }
        reviewServiceFacade.updateReview(reviewId, reviewRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewServiceFacade.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
