package com.kata.cinema.base.rest.admin.AdminCountryRestController;

import com.kata.cinema.base.SpringContextTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/after.sql")
public class GetCountriesMovieByMovieIdTest extends SpringContextTest {


    /**
     * ТЕСТ-КЕЙС
     * Успешное получение списка списка стран по id фильма
     */
    @Test
    void givenListCountriesOnMovieId_successTest() throws Exception {

        mockMvc.perform(get("/api/countries/{id}", 21)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("[0].id", Is.is(27L)))
                .andExpect(jsonPath("[0].name", Is.is("USA")))

                .andExpect(jsonPath("[1].id", Is.is(91L)))
                .andExpect(jsonPath("[1].name", Is.is("China")));
    }
}
