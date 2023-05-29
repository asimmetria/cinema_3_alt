package com.kata.cinema.base.webapp.rest.user;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.webapp.facade.unauthorized.ReviewServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
