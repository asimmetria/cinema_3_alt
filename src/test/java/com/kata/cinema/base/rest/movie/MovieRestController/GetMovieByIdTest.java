package com.kata.cinema.base.rest.movie.MovieRestController;

import com.kata.cinema.base.SpringContextTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/movie/MovieRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/movie/MovieRestController/after.sql")
public class GetMovieByIdTest extends SpringContextTest {

    /**
     * ТЕСТ-КЕЙС
     * Успешное получение фильма по id
     */
    @Test
    void getMovieById_successTest() throws Exception {
        mockMvc.perform(get("/api/movies/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(14)))
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.name", Is.is("Cool")))
                .andExpect(jsonPath("$.originalName", Is.is("Крутой")))
                .andExpect(jsonPath("$.time", Is.is(100)))
                .andExpect(jsonPath("$.type", Is.is("MOVIE")))
                .andExpect(jsonPath("$.dateRelease", Is.is("04.03.2023")))
                .andExpect(jsonPath("$.rars", Is.is("ZERO_PLUS")))
                .andExpect(jsonPath("$.mpaa", Is.is("GENERAL_AUDIENCES")))
                .andExpect(jsonPath("$.description", Is.is("First description")))

                .andExpect(jsonPath("$.genres[0].id", Is.is(1)))
                .andExpect(jsonPath("$.genres[0].name", Is.is("Drama")))
                .andExpect(jsonPath("$.genres[1].id", Is.is(2)))
                .andExpect(jsonPath("$.genres[1].name", Is.is("Comedy")))

                .andExpect(jsonPath("$.score", Is.is(150.0)))
                .andExpect(jsonPath("$.countScore", Is.is(2)))

                .andExpect(jsonPath("$.casts[0].professionId", Is.is(1)))
                .andExpect(jsonPath("$.casts[0].professionName", Is.is("Actor")))
                .andExpect(jsonPath("$.casts[0].persons[0].personId", Is.is(1)))
                .andExpect(jsonPath("$.casts[0].persons[0].fullName", Is.is("Ivan Ivanov")))
                .andExpect(jsonPath("$.casts[0].persons[0].originalFullName", Is.is("IIII IIIIII")))
                .andExpect(jsonPath("$.casts[0].persons[1].personId", Is.is(3)))
                .andExpect(jsonPath("$.casts[0].persons[1].fullName", Is.is("Vlada Sidorova")))
                .andExpect(jsonPath("$.casts[0].persons[1].originalFullName", Is.is("XXX XXXXX")))

                .andExpect(jsonPath("$.casts[1].professionId", Is.is(2)))
                .andExpect(jsonPath("$.casts[1].professionName", Is.is("Director")))
                .andExpect(jsonPath("$.casts[1].persons[0].personId", Is.is(1)))
                .andExpect(jsonPath("$.casts[1].persons[0].fullName", Is.is("Ivan Ivanov")))
                .andExpect(jsonPath("$.casts[1].persons[0].originalFullName", Is.is("IIII IIIIII")))
                .andExpect(jsonPath("$.casts[1].persons[1].personId", Is.is(2)))
                .andExpect(jsonPath("$.casts[1].persons[1].fullName", Is.is("Petr Petrov")))
                .andExpect(jsonPath("$.casts[1].persons[1].originalFullName", Is.is("VVV VVVVVV")));
    }
}
