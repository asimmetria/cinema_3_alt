package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review getReviewById(Long id);

    @Query("SELECT r FROM Review r WHERE r.isModerate = true ")
    List<Review> getReviewsByMovieId(Long id, Pageable pageable);

    boolean existsReviewById(Long id);


}
