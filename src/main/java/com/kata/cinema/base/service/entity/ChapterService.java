package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entitys.Chapter;

public interface ChapterService {
    Chapter createChapter(String name);

    Chapter updateChapter(Long id, String name);

    void deleteChapter(Long id);
}

