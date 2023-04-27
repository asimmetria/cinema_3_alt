package com.kata.cinema.base.rest.admin.AdminMovieRestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/movie/AdminMovieRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/movie/AdminMovieRestController/after.sql")
public class AdminMovieRestControllerTest extends SpringContextTest {

    @Autowired
    JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    public void init() {
        token = jwtUtil.generateToken();
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное добавление фильма в бд
     */
    @Test
    void postMovie_successTest() throws Exception {
        List<Long> genreIds = new ArrayList<>();
        List<Long> countryIds = new ArrayList<>();
        genreIds.add(1L);
        countryIds.add(2L);
        MovieRequestDto movieDto = new MovieRequestDto();
        movieDto.setName("Cool");
        movieDto.setRars(RARS.SIX_PLUS);
        movieDto.setMpaa(MPAA.GENERAL_AUDIENCES);
        movieDto.setTime(100);
        movieDto.setDescription("First description");
        movieDto.setOriginalName("Крутой");
        movieDto.setGenreIds(genreIds);
        movieDto.setCountryIds(countryIds);

        MvcResult mvcResult = mockMvc.perform(post("/api/admin/movies")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movieDto)))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение фильма в бд
     */
    @Test
    void putMovie_successTest() throws Exception {
        List<Long> genreIds = new ArrayList<>();
        List<Long> countryIds = new ArrayList<>();
        genreIds.add(1L);
        genreIds.add(2L);
        countryIds.add(1L);
        MovieRequestDto movieDto = new MovieRequestDto();
        movieDto.setName("Cool 2");
        movieDto.setRars(RARS.SIX_PLUS);
        movieDto.setMpaa(MPAA.GENERAL_AUDIENCES);
        movieDto.setTime(200);
        movieDto.setDescription("Second description");
        movieDto.setOriginalName("Крутой 2");
        movieDto.setGenreIds(genreIds);
        movieDto.setCountryIds(countryIds);

        MvcResult mvcResult = mockMvc.perform(put("/api/admin/movies/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movieDto)))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }
}
