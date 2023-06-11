package com.kata.cinema.base.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.models.entitys.Chapter;
import com.kata.cinema.base.repository.ChapterRepository;
import com.kata.cinema.base.webapp.rest.ChapterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ChapterController.class)
@AutoConfigureMockMvc
public class ChapterControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ChapterRepository chapterRepository;

    @Test
    public void createChapter_ReturnsCreatedStatus() throws Exception {
        String chapterName = "Test Chapter";

        Chapter chapter = new Chapter();
        chapter.setId(1L);
        chapter.setName(chapterName);

        when(chapterRepository.save(any(Chapter.class))).thenReturn(chapter);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/chapters")
                        .param("name", chapterName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(chapterName));
    }

    @Test
    public void updateChapter_ReturnsUpdatedChapter() throws Exception {
        Long chapterId = 1L;
        String updatedChapterName = "Updated Chapter";

        Chapter existingChapter = new Chapter();
        existingChapter.setId(chapterId);
        existingChapter.setName("Test Chapter");

        Chapter updatedChapter = new Chapter();
        updatedChapter.setId(chapterId);
        updatedChapter.setName(updatedChapterName);

        when(chapterRepository.findById(chapterId)).thenReturn(Optional.of(existingChapter));
        when(chapterRepository.save(any(Chapter.class))).thenReturn(updatedChapter);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/chapters/{id}", chapterId)
                        .param("name", updatedChapterName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(chapterId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedChapterName));
    }

    @Test
    public void deleteChapter_ReturnsNoContent() throws Exception {
        Long chapterId = 1L;

        Chapter existingChapter = new Chapter();
        existingChapter.setId(chapterId);
        existingChapter.setName("Test Chapter");

        when(chapterRepository.findById(chapterId)).thenReturn(Optional.of(existingChapter));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/chapters/{id}", chapterId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
