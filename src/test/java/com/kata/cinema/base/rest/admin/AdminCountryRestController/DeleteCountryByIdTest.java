package com.kata.cinema.base.rest.admin.AdminCountryRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.rest.util.IntegrationTestingAccessTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCountryRestController/after.sql")
public class DeleteCountryByIdTest extends SpringContextTest {


    /*
     * ТЕСТ-КЕЙС
     * Успешное удаление страны по id
     */
    @Test
    void deleteCountryById_successTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        mockMvc.perform(delete("/api/admin/countries/{id}", 33)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача, страны с таким id не существует
     */

    @Test
    void deleteCountryById_failedTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        mockMvc.perform(delete("/api/admin/countries/{id}", 11)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
