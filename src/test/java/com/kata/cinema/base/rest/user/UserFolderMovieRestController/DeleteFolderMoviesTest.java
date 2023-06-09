package com.kata.cinema.base.rest.user.UserFolderMovieRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.exception.NotFoundEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMovieRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMovieRestController/after.sql")
public class DeleteFolderMoviesTest extends SpringContextTest {

    private String token;

    /*
     * ТЕСТ-КЕЙС
     * Успешное удаление фолдера
     */
    @Test
    void deleteFolderMoviesById_successTest() throws Exception {
        mockMvc.perform(delete("/api/user/folders/movies/{id}", 102L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача, фолдера не существует
     */
    @Test
    void deleteFolderMoviesById_failedTest() throws Exception {
        mockMvc.perform(delete("/api/user/folders/movies/{id}", 999L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundEntityException))
                .andExpect(result -> assertEquals("Папка с таким id = 999 не существует",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
