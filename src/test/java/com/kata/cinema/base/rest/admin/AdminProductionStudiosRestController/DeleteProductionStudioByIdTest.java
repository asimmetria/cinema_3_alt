package com.kata.cinema.base.rest.admin.AdminProductionStudiosRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.rest.util.IntegrationTestingAccessTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminProductionStudiosRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminProductionStudiosRestController/after.sql")
public class DeleteProductionStudioByIdTest extends SpringContextTest {

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление по id
     */

    @Test
    void deleteProductionStudioById_successTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);
        mockMvc.perform(delete("/api/admin/studios/{id}", 15)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, с таким id не существует
     */

    @Test
    void deleteProductionStudioById_failedTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        mockMvc.perform(delete("/api/admin/studios/{id}", 15)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));
    }

}
