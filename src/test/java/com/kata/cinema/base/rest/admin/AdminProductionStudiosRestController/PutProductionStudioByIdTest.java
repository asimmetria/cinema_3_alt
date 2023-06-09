package com.kata.cinema.base.rest.admin.AdminProductionStudiosRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.ProductionStudiosRequestDto;
import com.kata.cinema.base.rest.util.IntegrationTestingAccessTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminProductionStudiosRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminProductionStudiosRestController/after.sql")
public class PutProductionStudioByIdTest extends SpringContextTest {
    private String token;

    /**
     * ТЕСТ-КЕЙС
     * Успешное изменение ProductionStudio
     */

    @Test
    void putProductionStudio_successTest() throws Exception {
        token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        ProductionStudiosRequestDto body = new ProductionStudiosRequestDto();
        body.setName("ProductionStudios");
        body.setDescription("test_update");
        body.setDateFoundation(LocalDate.now());

        mockMvc.perform(put("/api/admin/studios/{id}", 15)
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());
    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, id не существует
     */

    @Test
    void putProductionStudio_failedTest() throws Exception {
        token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        ProductionStudiosRequestDto body = new ProductionStudiosRequestDto();
        body.setName("ProductionStudios");
        body.setDescription("test_update2");
        body.setDateFoundation(LocalDate.now());

        mockMvc.perform(put("/api/admin/studios/{id}", 1001)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
