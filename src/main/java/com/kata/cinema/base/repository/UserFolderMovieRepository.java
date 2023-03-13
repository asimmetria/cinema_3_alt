package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.FolderMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFolderMovieRepository extends JpaRepository<FolderMovie, Long> {
}
