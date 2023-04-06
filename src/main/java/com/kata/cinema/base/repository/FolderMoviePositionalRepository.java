package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderMoviePositionalRepository extends JpaRepository<FolderMoviePositional, Long> {

    @Query("SELECT new com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto(" +
            "fmp.id," +
            "fmp.movie," +
            "fmp.folder," +
            "fmp.positional) FROM FolderMoviePositional fmp WHERE fmp.folder.id = :folderId AND fmp.movie.id = :movieId")
    FolderMoviePositionalResponseDto getFolderMoviePositionalByFolderAndMovie(@Param("folderId") Long folderId, @Param("movieId") Long movieId);

    @Query("SELECT new com.kata.cinema.base.models.dto.response.FolderMoviePositionalResponseDto(" +
            "fmp.id," +
            "fmp.movie," +
            "fmp.folder," +
            "fmp.positional) FROM FolderMoviePositional fmp")
    List<FolderMoviePositionalResponseDto> getAll();
}

