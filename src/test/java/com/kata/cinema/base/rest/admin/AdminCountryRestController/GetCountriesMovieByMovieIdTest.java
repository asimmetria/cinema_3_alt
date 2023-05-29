package com.kata.cinema.base.rest.admin.AdminCountryRestController;

import com.kata.cinema.base.SpringContextTest;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class GetCountriesMovieByMovieIdTest extends SpringContextTest {



    private String token;



    /**
     * ТЕСТ-КЕЙС
     * Успешное получение списка стран по id фильма
     */
    @Test
    void givenListCountriesOnMovieId_successTest() throws Exception {
        mockMvc.perform(get("/api/countries/{id}", 21)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("[0].id", Is.is(27)))
                .andExpect(jsonPath("[0].name", Is.is("USA")))
                .andExpect(jsonPath("[1].id", Is.is(91)))
                .andExpect(jsonPath("[1].name", Is.is("China")));
    }


    /**
     * ТЕСТ-КЕЙС
     * Неудача при получении списка стран, по несуществующему фильму
     */
    @Test
    void givenListCountriesOnMovieId_failedTest() throws Exception {
        mockMvc.perform(get("/api/countries/{id}", 90)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
