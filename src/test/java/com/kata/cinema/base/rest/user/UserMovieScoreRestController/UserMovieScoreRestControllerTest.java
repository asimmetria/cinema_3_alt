package com.kata.cinema.base.rest.user.UserMovieScoreRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.entitys.Score;
import com.kata.cinema.base.repository.ScoreRepository;
import com.kata.cinema.base.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserMovieScoreRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserMovieScoreRestController/after.sql")
public class UserMovieScoreRestControllerTest extends SpringContextTest {


    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    public void init() {
        token = jwtUtil.generateToken();
    }


    /**
     * ТЕСТ-КЕЙС
     * Успешное создание оценки
     */
    @Test
    void givenMovieIdAndUserIdAndScore_whenCreateMovieScore_thenScoreCreatedAndDatabaseUpdated_successTest() throws Exception {
        // Given
        Long movieId = 100L;
        Integer score = 5;
        Long userId = 102L;

        // When
        mockMvc.perform(post("/api/user/movies/{id}/score", movieId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("score", String.valueOf(score))
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        // Then
        // Проверяем, что изменения внесены в базу данных
        Long createdScoreId = jdbcTemplate.queryForObject(
                "SELECT id FROM score WHERE movie_id = ? AND user_id = ?", Long.class, movieId, userId);
        assertNotNull(createdScoreId);
        assertNotNull(scoreRepository.findById(createdScoreId).orElse(null));
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение оценки
     */
    @Test
    void givenMovieIdAndScoreIdAndUpdatedScore_whenUpdateMovieScore_thenScoreUpdatedAndDatabaseUpdated_successTest() throws Exception {
        // Given
        Long movieId = 101L;
        Long scoreId = 102L;
        Integer newScore = 5;

        // When
        mockMvc.perform(patch("/api/user/movies/{id}/score/{scoreId}", movieId, scoreId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("score", String.valueOf(newScore))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверяем, что изменения внесены в базу данных
        Score updatedScore = scoreRepository.findById(scoreId).orElse(null);
        assertNotNull(updatedScore);
        assertEquals(5, updatedScore.getScore());
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление оценки
     */
    @Test
    void givenMovieIdAndScoreId_whenDeleteMovieScore_thenScoreDeletedAndDatabaseUpdated_successTest() throws Exception {
        // Given
        Long movieId = 101L;
        Long deletedScoreId = 102L;

        // When
        mockMvc.perform(delete("/api/user/movies/{id}/score/{scoreId}", movieId, deletedScoreId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
        // Проверяем, что изменения внесены в базу данных
        assertNull(scoreRepository.findById(deletedScoreId).orElse(null));
    }

}
