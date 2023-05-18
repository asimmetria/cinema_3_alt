package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ReviewPaginationService {
    Page<ReviewResponseDto> getMovieReview(Long movieId, int pageNumber, int size,
                                           ReviewSortType sortType, Optional<TypeReview> typeReview);
}
