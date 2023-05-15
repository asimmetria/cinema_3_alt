package com.kata.cinema.base.webapp.facade.unauthorized.impl;

import com.kata.cinema.base.converter.review.ReviewMapper;
import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.entitys.Review;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.entity.ReviewService;
import com.kata.cinema.base.webapp.facade.unauthorized.ReviewServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewServiceFacadeImpl implements ReviewServiceFacade {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Override
    public Page<ReviewResponseDto> getMovieReview(Long movieId, int pageNumber, int size,
                                                  ReviewSortType sortType, Optional<TypeReview> typeReview) {
        Pageable pageable = PageRequest.of(pageNumber, size, sortType.getSortType());

        List<Review> reviewList = null;
        System.out.println("facade");

        if (typeReview.isPresent()) {
            System.out.println("isPresent");

            reviewList = reviewService.getReviewsByMovieId(movieId, pageable)
                    .stream()
                    .filter(r -> r.isModerate())
                    .filter(r -> r.getTypeReview() == typeReview.get())
                    .collect(Collectors.toList());
        } else {
            System.out.println("noPresent");
            reviewList = reviewService.getReviewsByMovieId(movieId, pageable)
                    .stream()
                    .filter(r -> r.isModerate())
                    .collect(Collectors.toList());
        }

        Page<Review> entityPage = new PageImpl<>(reviewList, pageable, reviewList.size());

        return entityPage.map(reviewMapper::toDto);
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
