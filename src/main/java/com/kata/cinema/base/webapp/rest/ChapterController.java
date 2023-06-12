package com.kata.cinema.base.webapp.rest;

import com.kata.cinema.base.models.dto.response.ChapterResponseDto;
import com.kata.cinema.base.models.entitys.Chapter;
import com.kata.cinema.base.service.entity.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/chapters")
public class ChapterController {
    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping
    public ResponseEntity<ChapterResponseDto> createChapter(@RequestParam String name) {
        Chapter createdChapter = chapterService.createChapter(name);
        ChapterResponseDto chapterDto = mapChapterToDto(createdChapter);
        return ResponseEntity.status(HttpStatus.CREATED).body(chapterDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChapterResponseDto> updateChapter(@PathVariable Long id, @RequestParam String name) {
        Chapter updatedChapter = chapterService.updateChapter(id, name);
        ChapterResponseDto chapterDto = mapChapterToDto(updatedChapter);
        return ResponseEntity.ok(chapterDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapter(id);
        return ResponseEntity.noContent().build();
    }

    // Вспомогательный метод для маппинга Chapter на ChapterResponsDto
    private ChapterResponseDto mapChapterToDto(Chapter chapter) {
        ChapterResponseDto chapterDto = new ChapterResponseDto();
        chapterDto.setId(chapter.getId());
        chapterDto.setName(chapter.getName());
        return chapterDto;
    }
}

