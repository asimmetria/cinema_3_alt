package com.kata.cinema.base.rest.collection.CollectoinRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/collection/CollectionRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/collection/CollectionRestController/after.sql")
public class UpdateCollectionTest extends SpringContextTest {

    /*
     * ТЕСТ-КЕЙС
     * Успешное обновление коллекции
     */

    @Test
    void updateCollection_successTest() throws Exception {
        CollectionRequestDto body = new CollectionRequestDto();
        body.setName("collection");
        body.setDescription("test_collection");
        mockMvc.perform(put("/api/collections/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk());
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача при отсутвии тела запроса
     */

    @Test
    void updateCollectionWithoutRequestBody_failedTest() throws Exception {
        mockMvc.perform(put("/api/collections/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
