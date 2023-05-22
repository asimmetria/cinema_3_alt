package com.kata.cinema.base.rest.admin.AdminProductionStudiosRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.rest.util.IntegrationTestingAccessTokenUtil2;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminProductionStudiosRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminProductionStudiosRestController/after.sql")
public class PostProductionStudioTest extends SpringContextTest {
    private String token;

    /**
     * ТЕСТ-КЕЙС
     * Успешное добавление ProductionStudio
     */

    @Test
    void postProductionStudio_successTest() throws Exception {

        token = IntegrationTestingAccessTokenUtil2.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        ProductionStudiosRequestDto body = new ProductionStudiosRequestDto();
        body.setName("ProductionStudios");
        body.setDescription("test_description");
        body.setDateFoundation("2004-02-22");
        mockMvc.perform(post("/api/admin/studios")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, при отсутвии тела запроса уже добавлена
     */

    @Test
    void postProductionStudio_failedTest() throws Exception {
        token = IntegrationTestingAccessTokenUtil2.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        mockMvc.perform(post("/api/admin/studios")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

