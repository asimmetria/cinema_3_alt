package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FolderMoviePositionalRepository extends JpaRepository<FolderMoviePositional, Long> {

    @Query("SELECT new com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto( " +
            "fmp.id, " +
            "fmp.movie, " +
            "fmp.folder, " +
            "fmp.positional) " +
            "FROM FolderMoviePositional fmp WHERE fmp.folder.id = :folderId AND fmp.movie.id = :movieId")
    FolderMoviePositionalResponseDto getByFolderIdAndMovieId(@Param("folderId") Long folderId, @Param("movieId") Long movieId);

    @Query("SELECT fmp.positional FROM FolderMoviePositional fmp ORDER BY fmp.id DESC LIMIT 1")
    Integer getLastMoviePosition();
}

