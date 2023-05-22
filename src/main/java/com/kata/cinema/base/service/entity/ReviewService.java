package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.entitys.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    void save(Long reviewId, Long movieId, ReviewRequestDto reviewRequestDto);

    boolean reviewIsExist(Long id);

    void deleteById(Long id);

    List<Review> getReviewsByMovieId(Long id, Pageable pageable);
}
