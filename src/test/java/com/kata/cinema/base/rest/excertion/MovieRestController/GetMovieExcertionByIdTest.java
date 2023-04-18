package com.kata.cinema.base.rest.excertion.MovieRestController;

import com.kata.cinema.base.SpringContextTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/excertion/MovieRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/excertion/MovieRestController/after.sql")
public class GetMovieExcertionByIdTest extends SpringContextTest {

    /**
     * ТЕСТ-КЕЙС
     * Успешное получение выдержек из фильма по id
     */
    @Test
    void getMovieExcertionById_successTest() throws Exception {
        mockMvc.perform(get("/api/movies/1/excertions/page/1?itemsOnPage=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(11)))
                .andExpect(jsonPath("$.content[0].id", Is.is(1)))
                .andExpect(jsonPath("$.content[0].description", Is.is("The first movie description")))
                .andExpect(jsonPath("$.content[1].id", Is.is(2)))
                .andExpect(jsonPath("$.content[1].description", Is.is("The second movie description")));
    }
}
