package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.models.entitys.Review;
import com.kata.cinema.base.models.enums.TypeReview;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByMovieId(Long id, TypeReview typeReview, Pageable pageable);

    List<ReviewTitleResponseDto> getAllReviews(String isModerate);

    void save(Long movieId, Long userId, ReviewRequestDto reviewRequestDto);

    void update(Long reviewId, ReviewRequestDto reviewRequestDto);

    void deleteById(Long id);

    boolean reviewIsExist(Long id);

    void isModerate(long id);

}
