package com.kata.cinema.base.rest.admin.AdminCountryRestController;

import com.kata.cinema.base.SpringContextTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        mockMvc.perform(post("/apt/admin/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "India"))
                .andExpect(status().isOk());
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача, страна уже добавлена
     */

    @Test
    void postCountryOnName_failedTest() throws Exception {
        mockMvc.perform(post("/apt/admin/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Russia"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.text", Is.is("Страна - Russia уже добавлена")));
    }


}
