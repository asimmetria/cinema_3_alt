package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.dto.response.FolderMovieResponseDto;
import com.kata.cinema.base.models.entitys.FolderMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFolderMovieRepository extends JpaRepository<FolderMovie, Long> {

    @Query("SELECT new com.kata.cinema.base.models.dto.response.FolderMovieResponseDto(" +
            "fm.id," +
            "fm.privacy," +
            "fm.name," +
            "fm.description," +
            "fm.folderMovieType) FROM FolderMovie fm WHERE fm.user.id = :userId")
    List<FolderMovieResponseDto> getFolderMovieByUserId(@Param("userId") Long userId);
}
