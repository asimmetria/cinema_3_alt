package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderMoviePositionalRepository extends JpaRepository<FolderMoviePositional, Long> {

    @Query("SELECT fmp FROM FolderMoviePositional fmp "
            + "JOIN FETCH fmp.folder f "
            + "JOIN FETCH fmp.movie m "
            + "WHERE f.id = :folderId "
            + "AND m.id = :movieId")
    FolderMoviePositional getByFolderIdAndMovieId(@Param("folderId") Long folderId, @Param("movieId") Long movieId);

    @Query("FROM FolderMoviePositional fmp "
            + "WHERE fmp.positional > :position "
            + "ORDER BY fmp.positional")
    List<FolderMoviePositional> getAllNextByPositionOrdered(@Param("position") Integer position);

    @Query("FROM FolderMoviePositional fmp "
            + "WHERE fmp.positional > :startPosition "
            + "AND fmp.positional <= :endPosition "
            + "ORDER BY fmp.positional")
    List<FolderMoviePositional> getAllBetweenTwoPositionsOrderedPositionIncreased(@Param("startPosition") Integer startPosition,
                                                          @Param("endPosition") Integer endPosition);

    @Query("FROM FolderMoviePositional fmp "
            + "WHERE fmp.positional < :startPosition "
            + "AND fmp.positional >= :endPosition "
            + "ORDER BY fmp.positional")
    List<FolderMoviePositional> getAllBetweenTwoPositionsOrderedPositionDecreased(@Param("startPosition") Integer startPosition,
                                                                 @Param("endPosition") Integer endPosition);

    @Query("SELECT fmp.positional FROM FolderMoviePositional fmp ORDER BY fmp.id DESC LIMIT 1")
    Integer getLastMoviePosition();
}

