package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.exception.ChapterNotFoundException;
import com.kata.cinema.base.models.entitys.Chapter;
import com.kata.cinema.base.repository.ChapterRepository;
import com.kata.cinema.base.service.entity.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepository chapterRepository;

    @Autowired
    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Override
    public Chapter createChapter(String name) {
        Chapter chapter = new Chapter();
        chapter.setName(name);
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter updateChapter(Long id, String name) {
        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ChapterNotFoundException("Chapter not found with id: " + id));

        chapter.setName(name);
        return chapterRepository.save(chapter);
    }

    @Override
    public void deleteChapter(Long id) {
        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ChapterNotFoundException("Chapter not found with id: " + id));

        chapterRepository.delete(chapter);
    }
}


