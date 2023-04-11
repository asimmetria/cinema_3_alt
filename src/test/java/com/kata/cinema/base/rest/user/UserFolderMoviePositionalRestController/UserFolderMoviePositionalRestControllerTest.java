package com.kata.cinema.base.rest.user.UserFolderMoviePositionalRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.webapp.facade.movie.UserFolderMoviePositionalServiceFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;



@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMoviePositionalRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMoviePositionalRestController/after.sql")
public class UserFolderMoviePositionalRestControllerTest extends SpringContextTest {

    @Autowired
    private UserFolderMoviePositionalServiceFacade userFolderMoviePositionalServiceFacade;

    /**
     * ТЕСТ-КЕЙС
     * Успешное добавление фильма в фолдер
     */
    @Test
    void givenFolderIdAndMovieId_whenAddMovieToFolder_thenSuccessTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 100L;

        // When
        mockMvc.perform(post("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        // Проверка, что фильм был добавлен в фолдер
        FolderMoviePositional folderMoviePositional = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNotNull(folderMoviePositional);
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение позиции фильма в фолдер
     */
    @Test
    void givenFolderIdAndMovieIdAndPosition_whenChangeMoviePositionInFolder_thenSuccessTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 100L;
        Integer position = 1;
        Integer newPosition = 2;

        // When
        mockMvc.perform(put("/api/user/folders/{id}/movies/{movieId}?position={position}", id, movieId, position)
                        .param("positional", String.valueOf(newPosition))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция фильма была обновлена в фолдер
        FolderMoviePositional folderMoviePositional = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNotNull(folderMoviePositional);
        assertEquals(newPosition, folderMoviePositional.getPositional());
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление фильма из фолдер
     */
    @Test
    void givenFolderIdAndMovieId_whenDeleteMovieFromFolder_thenSuccessTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 100L;

        // When
        mockMvc.perform(delete("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        // Проверка, что фильмы был удален из фолдер
        FolderMoviePositional folderMoviePositional = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNull(folderMoviePositional);
    }

}