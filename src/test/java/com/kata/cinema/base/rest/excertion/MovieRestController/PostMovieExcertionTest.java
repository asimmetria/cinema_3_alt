package com.kata.cinema.base.rest.excertion.MovieRestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PostMovieExcertionTest extends SpringContextTest {

    /**
     * ТЕСТ-КЕЙС
     * Успешное добавление выдержки из фильма
     */
    @Test
    void postMovieExcertion_successTest() throws Exception {
        ExcertionRequestDto excertionDto = new ExcertionRequestDto();
        excertionDto.setDescription("The first movie excertion");

        MvcResult mvcResult = mockMvc.perform(post("/api/movies/1/excertions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(excertionDto)))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }
}
