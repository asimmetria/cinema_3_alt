package com.kata.cinema.base.rest.excertion.ExcertionRestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/excertion/MovieRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/excertion/MovieRestController/after.sql")
public class ExcertionRestControllerTest extends SpringContextTest {

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение выдержки по id
     */
    @Test
    void updateExcertionById_successTest() throws Exception {
        ExcertionRequestDto excertionDto = new ExcertionRequestDto();
        excertionDto.setDescription("Converted description");

        MvcResult mvcResult = mockMvc.perform(put("/api/excertions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(excertionDto)))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление выдержки по id
     */
    @Test
    void deleteExcertionById_successTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/api/excertions/1")).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }
}
