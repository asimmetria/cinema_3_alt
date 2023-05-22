package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.converter.review.ReviewMapper;
import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.entitys.Movie;
import com.kata.cinema.base.models.entitys.Review;
import com.kata.cinema.base.repository.ReviewRepository;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.ReviewService;
import com.kata.cinema.base.service.entity.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final MovieService movieService;
    private final UserService userService;


    @Override
    public List<Review> getReviewsByMovieId(Long id, Pageable pageable) {
        return reviewRepository.getReviewsByMovieId(id, pageable);
    }

    @Override
    public void save(Long reviewId, Long movieId, ReviewRequestDto reviewRequestDto) {
        Review convertedReview = reviewMapper.toEntity(reviewRequestDto);

        if (reviewIsExist(reviewId)) {
            Review review = reviewRepository.getReviewById(reviewId);
            convertedReview.setId(reviewId);
            convertedReview.setMovie(review.getMovie());
            convertedReview.setUser(userService.getById(reviewRequestDto.getUserId()));
        } else {
            Movie movie = movieService.getMovie(movieId);
            convertedReview.setMovie(movie);
            convertedReview.setUser(userService.getById(reviewRequestDto.getUserId()));
        }
        reviewRepository.save(convertedReview);
    }

    @Override
    public boolean reviewIsExist(Long id) {
        return reviewRepository.existsReviewById(id);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

}
