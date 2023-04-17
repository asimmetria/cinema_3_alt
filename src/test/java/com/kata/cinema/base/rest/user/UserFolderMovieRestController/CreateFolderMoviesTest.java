package com.kata.cinema.base.rest.user.UserFolderMovieRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMovieRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderMovieRestController/after.sql")
public class CreateFolderMoviesTest extends SpringContextTest {


    /*
     * ТЕСТ-КЕЙС
     * Успешное создание фолдера пользователя
     */

    @Test
    void creatingFolderForUser_successTest() throws Exception {

        mockMvc.perform(post("/api/user/folders/movies")
                .param("userId", String.valueOf(100L))
                .contentType(MediaType.APPLICATION_JSON));
    }

    /*
     * ТЕСТ-КЕЙС
     * Неудача при добавлении пустой DTO
     */

    @Test
    void creatingFolderForUser_withNullRequestDto_failedTest() throws Exception {
        mockMvc.perform(post("/api/user/folders/movies")
                .param("userId", String.valueOf(102L))
                .param("folderRequestDto", String.valueOf(new FolderRequestDto()))
                .contentType(MediaType.APPLICATION_JSON));
        assertNull(folderService.findFolderByUserId(102L));
    }

}
