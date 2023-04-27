package com.kata.cinema.base.rest.user.UserFolderMoviePositionalRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.entitys.FolderMoviePositional;
import com.kata.cinema.base.rest.util.IntegrationTestingAccessTokenUtil;
import com.kata.cinema.base.webapp.facade.user.UserFolderMoviePositionalServiceFacade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;



@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMoviePositionalRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMoviePositionalRestController/after.sql")
public class UserFolderMoviePositionalRestControllerTest extends SpringContextTest {

    @Autowired
    private UserFolderMoviePositionalServiceFacade userFolderMoviePositionalServiceFacade;

    private String token;

    /**
     * ТЕСТ-КЕЙС
     * Успешное добавление фильма в фолдер
     */
    @Test
    void givenFolderIdAndMovieId_whenAddMovieToFolder_thenSuccessTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 105L;


        token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("email1@mail.ru", "password", mockMvc);
        // When
        mockMvc.perform(post("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что фильм был добавлен в фолдер и его позиция установлена корректно
        FolderMoviePositional folderMoviePositional = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNotNull(folderMoviePositional);
        assertEquals(6, folderMoviePositional.getPositional());

    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение позиции фильма в фолдер в сторону увеличения
     */
    @Test
    void givenFolderIdAndMovieIdAndPosition_whenIncreaseMoviePositionInFolder_thenUpdateOtherMoviesPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 100L;
        Integer position = 3;


        // When
        // Позиция фильма изменяется с 1 на 3
        mockMvc.perform(put("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .param("position", String.valueOf(position))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция фильма была обновлена в фолдер
        FolderMoviePositional folderMoviePositional1 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNotNull(folderMoviePositional1);
        assertEquals(position, folderMoviePositional1.getPositional());

        // Проверка, что позиции остальных фильмов были корректно обновлены
        FolderMoviePositional folderMoviePositional2 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 101L);
        FolderMoviePositional folderMoviePositional3 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 102L);
        FolderMoviePositional folderMoviePositional4 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 103L);
        FolderMoviePositional folderMoviePositional5 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 104L);
        assertEquals(position - 2, folderMoviePositional2.getPositional()); // position = 1 (-1)
        assertEquals(position - 1, folderMoviePositional3.getPositional()); // position = 2 (-1)
        assertEquals(position + 1, folderMoviePositional4.getPositional()); // position = 4 (не изменилась)
        assertEquals(position + 2, folderMoviePositional5.getPositional()); // position = 5 (не изменилась)
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение позиции фильма в фолдер в сторону уменьшения
     */
    @Test
    void givenFolderIdAndMovieIdAndPosition_whenDecreaseMoviePositionInFolder_thenUpdateOtherMoviesPositions_successTest() throws Exception {
        // Given
        Long id = 100L;
        Long movieId = 103L;
        Integer position = 2;


        // When
        // Позиция фильма изменяется с 4 на 2
        mockMvc.perform(put("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                        .param("position", String.valueOf(position))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что позиция фильма была обновлена в фолдер
        FolderMoviePositional folderMoviePositional4 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNotNull(folderMoviePositional4);
        assertEquals(position, folderMoviePositional4.getPositional());

        // Проверка, что позиции остальных фильмов были корректно обновлены
        FolderMoviePositional folderMoviePositional1 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 100L);
        FolderMoviePositional folderMoviePositional2 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 101L);
        FolderMoviePositional folderMoviePositional3 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 102L);
        FolderMoviePositional folderMoviePositional5 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 104L);

        assertEquals(position - 1, folderMoviePositional1.getPositional()); // position = 1 (не изменилась)
        assertEquals(position + 1, folderMoviePositional2.getPositional()); // position = 3 (+1)
        assertEquals(position + 2, folderMoviePositional3.getPositional()); // position = 4 (+1)
        assertEquals(position + 3, folderMoviePositional5.getPositional()); // position = 5 (не изменилась)
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
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверка, что фильм был удален из фолдер
        FolderMoviePositional folderMoviePositional1 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(id, movieId);
        assertNull(folderMoviePositional1);

        // Проверка, что позиции следующих фильмов были обновлены
        FolderMoviePositional folderMoviePositional2 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 101L);
        FolderMoviePositional folderMoviePositional3 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 102L);
        FolderMoviePositional folderMoviePositional4 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 103L);
        FolderMoviePositional folderMoviePositional5 = userFolderMoviePositionalServiceFacade.getByFolderIdAndMovieId(100L, 104L);
        assertEquals(deletedMoviePosition, folderMoviePositional2.getPositional());
        assertEquals(deletedMoviePosition + 1, folderMoviePositional3.getPositional());
        assertEquals(deletedMoviePosition + 2, folderMoviePositional4.getPositional());
        assertEquals(deletedMoviePosition + 3, folderMoviePositional5.getPositional());

    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, фильм с таким id не существует
     */
    @Test
    void givenFolderIdAndMovieIdAndPosition_whenChangeMoviePositionInFolder_thenFailedTest() {
        // Given
        Long id = 100L;
        Long movieId = 999L;
        Integer position = 2;

        // When
        try {
            mockMvc.perform(put("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                            .param("position", String.valueOf(position))
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            // Then
            assertEquals("Фильм с таким id = 999 не существует", e.getCause().getMessage());
        }

    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, фолдер с таким id не существует
     */
    @Test
    void givenFolderIdAndMovieId_whenAddMovieToFolder_thenFailedTest() {
        // Given
        Long id = 999L;
        Long movieId = 100L;

        // When
        try {
            mockMvc.perform(post("/api/user/folders/{id}/movies/{movieId}", id, movieId)
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            // Then
            assertEquals("Папка с таким id = 999 не существует", e.getCause().getMessage());
        }
    }
}