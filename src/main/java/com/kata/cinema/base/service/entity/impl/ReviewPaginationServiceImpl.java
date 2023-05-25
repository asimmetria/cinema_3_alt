package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.converter.review.ReviewMapper;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.entitys.Review;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.entity.ReviewPaginationService;
import com.kata.cinema.base.service.entity.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewPaginationServiceImpl implements ReviewPaginationService {
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @Override
    public Page<ReviewResponseDto> getMovieReview(Long movieId, int pageNumber, int size, ReviewSortType sortType, TypeReview typeReview) {

        Pageable pageable = PageRequest.of(pageNumber, size, sortType.getSortType());
        List<Review> reviewList = reviewService.getReviewsByMovieId(movieId, typeReview, pageable);
        Page<Review> entityPage = new PageImpl<>(reviewList, pageable, reviewList.size());

        return entityPage.map(reviewMapper::toDto);

    }
}
