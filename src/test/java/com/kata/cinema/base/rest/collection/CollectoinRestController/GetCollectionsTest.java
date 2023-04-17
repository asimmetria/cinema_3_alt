package com.kata.cinema.base.rest.collection.CollectoinRestController;

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


@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/collection/CollectionRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/collection/CollectionRestController/after.sql")
public class GetCollectionsTest extends SpringContextTest {

    /*
     * ТЕСТ-КЕЙС
     * Успешное получение коллекций
     */
    @Test
    void GivenAllCollectionsOnUserId_successTest() throws Exception {
        mockMvc.perform(get("/api/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(44L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("[0].id", Is.is(1)))
                .andExpect(jsonPath("[0].name", Is.is("Movies for dinner")))
                .andExpect(jsonPath("[0].collectionUrl", Is.is("/collection/1")))
                .andExpect(jsonPath("[0].countMovies", Is.is(2)))

                .andExpect(jsonPath("[1].id", Is.is(2)))
                .andExpect(jsonPath("[1].name", Is.is("Movies for children")))
                .andExpect(jsonPath("[1].collectionUrl", Is.is("/collection/2")))
                .andExpect(jsonPath("[1].countMovies", Is.is(2)));
    }

    /*
     * ТЕСТ-КЕЙС
     * Успешное получение коллекций по категории
     */
    @Test
    void GivenAllCollectionsOnUserIdAndCategoryId_successTest() throws Exception {
        mockMvc.perform(get("/api/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(44L))
                        .param("categoryId", String.valueOf(37L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("[0].id", Is.is(2)))
                .andExpect(jsonPath("[0].name", Is.is("Movies for children")))
                .andExpect(jsonPath("[0].collectionUrl", Is.is("/collection/2")))
                .andExpect(jsonPath("[0].countMovies", Is.is(2)));

    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача при несуществующем юзере
     */

    @Test
    void GivenAllCollectionsOnUserId_failedTest() throws Exception {
        mockMvc.perform(get("/api/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(99L)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundEntityException))
                .andExpect(result -> assertEquals("Пользователь с таким id = 99 не существует",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
