package com.kata.cinema.base.rest.media.MediaRestController;

import com.kata.cinema.base.SpringContextTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/media/MediaRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/media/MediaRestController/after.sql")
public class GetMediaByIdTest extends SpringContextTest {

    /**
     * ТЕСТ-КЕЙС
     * Успешное получение медиа по id
     */
    @Test
    void getMediaById_successTest() throws Exception {
        mockMvc.perform(get("/api/medias/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(7)))
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.title", Is.is("First title")))
                .andExpect(jsonPath("$.htmlBody", Is.is("The big text1")))
                .andExpect(jsonPath("$.countComment", Is.is(2)))
                .andExpect(jsonPath("$.author", Is.is("Alexander Pushkin")));
    }
}
