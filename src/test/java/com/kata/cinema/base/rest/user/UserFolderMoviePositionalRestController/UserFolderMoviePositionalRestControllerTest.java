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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        Long movieId = 103L;

        // When
        mockMvc.perform(post("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
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
    void givenFolderIdAndMovieIdAndPosition_whenChangeMoviePositionInFolder_thenUpdateNextMoviesPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 100L;
        Integer position = 2;


        // When
        mockMvc.perform(put("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .param("position", String.valueOf(position))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция фильма была обновлена в фолдер
        FolderMoviePositional folderMoviePositional = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNotNull(folderMoviePositional);
        assertEquals(position, folderMoviePositional.getPositional());

        // Проверка, что позиция следующих фильмов была обновлена
        FolderMoviePositional folderMoviePositional1 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 101L);
        FolderMoviePositional folderMoviePositional2 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 102L);
        assertEquals(position + 1, folderMoviePositional1.getPositional());
        assertEquals(position + 2, folderMoviePositional2.getPositional());
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление фильма из фолдер
     */
    @Test
    void givenFolderIdAndMovieId_whenDeleteMovieFromFolder_thenUpdateNextMoviesPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 100L;
        Integer deletedMoviePosition = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId).getPositional();

        // When
        mockMvc.perform(delete("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что фильмы был удален из фолдер
        FolderMoviePositional folderMoviePositional = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNull(folderMoviePositional);

        // Проверка, что позиция следующих фильма была обновлена
        FolderMoviePositional folderMoviePositional1 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 101L);
        FolderMoviePositional folderMoviePositional2 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 102L);
        assertEquals(deletedMoviePosition, folderMoviePositional1.getPositional());
        assertEquals(deletedMoviePosition + 1, folderMoviePositional2.getPositional());

    }

}