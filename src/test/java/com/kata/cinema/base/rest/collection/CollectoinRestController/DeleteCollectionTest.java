package com.kata.cinema.base.rest.collection.CollectoinRestController;

import com.kata.cinema.base.SpringContextTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/collection/CollectionRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/collection/CollectionRestController/after.sql")
public class DeleteCollectionTest extends SpringContextTest {

    /*
     * ТЕСТ-КЕЙС
     * Успешное удаление коллекции
     */
    @Test
    void deleteCollection_successTest() throws Exception {
        mockMvc.perform(delete("/api/collections/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
