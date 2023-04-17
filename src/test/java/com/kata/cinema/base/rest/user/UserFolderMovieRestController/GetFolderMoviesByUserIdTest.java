package com.kata.cinema.base.rest.user.UserFolderMovieRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.exception.NotFoundEntityException;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMovieRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMovieRestController/after.sql")
public class GetFolderMoviesByUserIdTest extends SpringContextTest {

    /**
     * ТЕСТ-КЕЙС
     * Успешное получение списка фолдеров пользователя
     */
    @Test
    void givenUserId_whenGetFolders_thenReturnFolderByUserId_successTest() throws Exception {
        mockMvc.perform(get("/api/user/folders/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(100L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].privacy", Is.is("PUBLIC")))
                .andExpect(jsonPath("$[0].name", Is.is("Новая папка1")))
                .andExpect(jsonPath("$[0].description", Is.is("описание")))
                .andExpect(jsonPath("$[0].type", Is.is("CUSTOM")))

                .andExpect(jsonPath("$[1].id", Is.is(101)))
                .andExpect(jsonPath("$[1].privacy", Is.is("PUBLIC")))
                .andExpect(jsonPath("$[1].name", Is.is("Новая папка2")))
                .andExpect(jsonPath("$[1].description", Is.is("описание")))
                .andExpect(jsonPath("$[1].type", Is.is("VIEWED_MOVIES")))

                .andExpect(jsonPath("$[2].id", Is.is(103)))
                .andExpect(jsonPath("$[2].privacy", Is.is("PUBLIC")))
                .andExpect(jsonPath("$[2].name", Is.is("Новая папка4")))
                .andExpect(jsonPath("$[2].description", Is.is("описание")))
                .andExpect(jsonPath("$[2].type", Is.is("FAVORITE_MOVIES")));
    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, пользователя с таким id не существует
     */
    @Test
    void givenUserId_whenGetFolders_thenReturnFolderByUserId_failedTest() throws Exception {
        mockMvc.perform(get("/api/user/folders/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(999L)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundEntityException))
                .andExpect(result -> assertEquals("Пользователь с таким id = 999 не существует",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
