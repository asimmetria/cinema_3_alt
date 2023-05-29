package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto;
import com.kata.cinema.base.models.entitys.Review;
import com.kata.cinema.base.models.enums.TypeReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review getReviewById(Long id);

    @Query("SELECT r FROM Review r WHERE r.movie.id = :id and r.isModerate = true and ( :type_review is null or r.typeReview = :type_review) ")
    List<Review> getReviewsByMovieId(@Param("id") Long id,
                                     @Param("type_review") TypeReview typeReview, Pageable pageable);

    @Query("SELECT new com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto (" +
            "r.id, r.title, r.date, r.movie.id, r.movie.name, r.movie.originalName) FROM Review r WHERE ( :isModerate is null or r.isModerate = :isModerate) ")
    List<ReviewTitleResponseDto> getAllReviewResponseByModerate(@Param("isModerate") boolean isModerate);

    @Query("SELECT new com.kata.cinema.base.models.dto.response.ReviewTitleResponseDto (" +
            "r.id, r.title, r.date, r.movie.id, r.movie.name, r.movie.originalName) FROM Review r")
    List<ReviewTitleResponseDto> getAllReviewResponse();

    boolean existsReviewById(Long id);

}
