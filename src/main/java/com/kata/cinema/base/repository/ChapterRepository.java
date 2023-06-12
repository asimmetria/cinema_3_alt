package com.kata.cinema.base.repository;

import com.kata.cinema.base.models.entitys.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}

