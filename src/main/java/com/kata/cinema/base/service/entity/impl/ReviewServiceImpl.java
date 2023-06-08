package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.converter.review.ReviewMapper;
import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.models.entitys.Review;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.repository.ReviewRepository;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.ReviewService;
import com.kata.cinema.base.service.entity.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final MovieService movieService;
    private final UserService userService;


    @Override
    public List<Review> getReviewsByMovieId(Long id, TypeReview typeReview, Pageable pageable) {
        return reviewRepository.getReviewsByMovieId(id, typeReview, pageable);
    }

    @Override
    public List<ReviewTitleResponseDto> getAllReviews(String isModerate) {
        List<ReviewTitleResponseDto> list = null;
        if (isModerate != null) {
            boolean mod = Boolean.parseBoolean(isModerate);
            list = reviewRepository.getAllReviewResponseByModerate(mod);
        } else {
            list = reviewRepository.getAllReviewResponse();
        }
        return list;
    }

    @Override
    public void save(Long movieId, Long userId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewMapper.toEntity(reviewRequestDto);
        LocalDate date = reviewRequestDto.getDate() != null ? reviewRequestDto.getDate() : LocalDate.now();

        review.setDate(date);
        review.setUser(userService.getById(userId));
        review.setMovie(movieService.getMovie(movieId));

        reviewRepository.save(review);
    }

    @Override
    public void update(Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepository.getReviewById(reviewId);
        LocalDate localDate = LocalDate.now();

        review.setDate(localDate);
        review.setDescription(reviewRequestDto.getDescription());
        review.setTitle(reviewRequestDto.getTitle());
        review.setTypeReview(reviewRequestDto.getTypeReview());

        reviewRepository.save(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public boolean reviewIsExist(Long id) {
        return reviewRepository.existsReviewById(id);
    }

    @Override
    public void isModerate(long id) {
        Review review = reviewRepository.getReviewById(id);
        review.setModerate(true);
        reviewRepository.save(review);
    }

}
