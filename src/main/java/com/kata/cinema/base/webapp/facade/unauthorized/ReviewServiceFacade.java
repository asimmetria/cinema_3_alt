package com.kata.cinema.base.webapp.facade.unauthorized;

import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ReviewServiceFacade {
    Page<ReviewResponseDto> getMovieReview(Long movieId, int pageNumber, int size, ReviewSortType sortType, Optional<TypeReview> typeReview);

    void createReview(Long id, ReviewRequestDto reviewRequestDto);

    void updateReview(Long id, ReviewRequestDto reviewRequestDto);

    void deleteReview(Long id);

}
