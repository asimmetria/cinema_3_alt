package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query("SELECT s FROM Score s "
            + "JOIN FETCH s.movie m "
            + "JOIN FETCH s.user u "
            + "WHERE s.id = :id ")
    Score getScoreById(@Param("id") Long id);
}
