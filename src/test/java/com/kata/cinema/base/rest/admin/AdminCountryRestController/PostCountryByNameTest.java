package com.kata.cinema.base.rest.admin.AdminCountryRestController;

import com.kata.cinema.base.SpringContextTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/after.sql")
public class PostCountryByNameTest extends SpringContextTest {

    /*
     * ТЕСТ-КЕЙС
     * Успешное изменение добавление страны
     */

    @Test
    void postCountryOnName_successTest() throws Exception {
        mockMvc.perform(post("/api/admin/countries")
                        .param("name", "India")
                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача, страна уже добавлена
     */

    @Test
    void postCountryOnName_failedTest() throws Exception {
        mockMvc.perform(post("/api/admin/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Russia"))
                .andExpect(status().isBadRequest());
    }


}
