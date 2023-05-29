package com.kata.cinema.base.webapp.facade.unauthorized;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewServiceFacade {

    Page<ReviewResponseDto> getPageReviewByMovie(Long movieId, int pageNumber, int size,
                                                 ReviewSortType sortType, TypeReview typeReview);

    List<ReviewTitleResponseDto> getListReviews(String isModerate);

    void createReview(Long movieId, Long userId, ReviewRequestDto reviewRequestDto);

    void updateReview(Long id, ReviewRequestDto reviewRequestDto);

    void deleteReview(Long id);

    boolean isExistReview(Long id);


}
