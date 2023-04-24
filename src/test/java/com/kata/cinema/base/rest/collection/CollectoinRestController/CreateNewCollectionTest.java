package com.kata.cinema.base.rest.collection.CollectoinRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.CollectionRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateNewCollectionTest extends SpringContextTest {


    /*
     * ТЕСТ-КЕЙС
     * Успешное создание коллекции
     */
    @Test
    void CreatingNewCollection_successTest() throws Exception {
        CollectionRequestDto body = new CollectionRequestDto();
        body.setName("collection");
        body.setDescription("test_collection");
        mockMvc.perform(post("/api/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача при отсутвии тела запроса
     */

    @Test
    void CreatingNewCollectionWithoutBody_failedTest() throws Exception {
        mockMvc.perform(post("/api/collections")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
