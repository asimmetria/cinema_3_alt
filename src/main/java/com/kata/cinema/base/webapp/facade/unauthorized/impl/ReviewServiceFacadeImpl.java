package com.kata.cinema.base.webapp.facade.unauthorized.impl;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.entity.ReviewPaginationService;
import com.kata.cinema.base.service.entity.ReviewService;
import com.kata.cinema.base.webapp.facade.unauthorized.ReviewServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewServiceFacadeImpl implements ReviewServiceFacade {
    private final ReviewService reviewService;
    private final ReviewPaginationService reviewPaginationService;

    @Override
    public Page<ReviewResponseDto> getPageReviewByMovie(Long movieId, int pageNumber, int size,
                                                        ReviewSortType sortType, TypeReview typeReview) {
        return reviewPaginationService.getMovieReview(movieId, pageNumber, size, sortType, typeReview);
    }

    @Override
    public List<ReviewTitleResponseDto> getListReviews(String isModerate) {
        return reviewService.getAllReviews(isModerate);
    }

    @Override
    public void createReview(Long movieId, Long userId, ReviewRequestDto reviewRequestDto) {
        reviewService.save(movieId, userId, reviewRequestDto);
    }

    @Override
    public void updateReview(Long reviewId, ReviewRequestDto reviewRequestDto) {
        reviewService.update(reviewId, reviewRequestDto);
    }

    @Override
    public void deleteReview(Long id) {
        reviewService.deleteById(id);
    }

    @Override
    public boolean isExistReview(Long id) {
        return reviewService.reviewIsExist(id);
    }
}
