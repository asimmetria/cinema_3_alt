package com.kata.cinema.base.rest.admin.AdminCountryRestController;

import com.kata.cinema.base.SpringContextTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/after.sql")
public class PutCountryByIdTest extends SpringContextTest {

    /*
     * ТЕСТ-КЕЙС
     * Успешное изменение страны по id
     */

    @Test
    void putCountryById_successTest() throws Exception {
        mockMvc.perform(put("/apt/admin/countries/{id}", 33)
                .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "UK"))
                .andExpect(status().isOk());
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача, страны с таким id нет
     */
    @Test
    void putCountryById_failedTest() throws Exception {
        mockMvc.perform(put("/apt/admin/countries/{id}", 50)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "UK"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.text", Is.is("Страны с id - 50 не существует")));
    }
}
