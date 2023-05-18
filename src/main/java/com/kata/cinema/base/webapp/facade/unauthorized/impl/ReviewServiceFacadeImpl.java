package com.kata.cinema.base.webapp.facade.unauthorized.impl;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.entity.ReviewPaginationService;
import com.kata.cinema.base.service.entity.ReviewService;
import com.kata.cinema.base.webapp.facade.unauthorized.ReviewServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReviewServiceFacadeImpl implements ReviewServiceFacade {
    private final ReviewService reviewService;
    private final ReviewPaginationService reviewPaginationService;

    @Override
    public Page<ReviewResponseDto> getPageReviewByMovie(Long movieId, int pageNumber, int size,
                                                        ReviewSortType sortType, Optional<TypeReview> typeReview) {
        return reviewPaginationService.getMovieReview(movieId, pageNumber, size, sortType, typeReview);
    }

    @Override
    public void createReview(Long movieId, ReviewRequestDto reviewRequestDto) {
        reviewService.save(null, movieId, reviewRequestDto);
    }

    @Override
    public void updateReview(Long reviewId, ReviewRequestDto reviewRequestDto) {
        reviewService.save(reviewId, null, reviewRequestDto);
    }

    @Override
    public void deleteReview(Long id) {
        reviewService.deleteById(id);
    }
}
