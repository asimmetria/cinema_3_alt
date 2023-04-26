package com.kata.cinema.base.rest.user.UserFolderPersonRestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.util.JwtUtil;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderPersonRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/user/UserFolderPersonRestController/after.sql")
public class UserFolderPersonRestControllerTest extends SpringContextTest {

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    public void init() {
        token = jwtUtil.generateToken();
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное получение списка фолдеров пользователя
     */
    @Test
    void givenUserId_whenGetFolders_thenReturnFolderByUserId_successTest() throws Exception {

        mockMvc.perform(get("/api/user/folders/persons")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(100L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].privacy", Is.is("PUBLIC")))
                .andExpect(jsonPath("$[0].name", Is.is("Новая папка1")))
                .andExpect(jsonPath("$[0].description", Is.is("описание")))
                .andExpect(jsonPath("$[0].type", Is.is("FAVOURITES")));
    }

    /**
     * ТЕСТ-КЕЙС
     * Неудача, пользователя с таким id не существует
     */
    @Test
    void givenUserId_whenGetFolders_thenReturnFolderByUserId_failedTest() throws Exception {
        try {
            mockMvc.perform(get("/api/user/folders/persons")
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("userId", String.valueOf(999L)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", Is.is("Пользователь с таким id = 999 не существует")));
        } catch (Exception e) {
            assertEquals("Пользователь с таким id = 999 не существует", e.getCause().getMessage());
        }
    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное создание новой папки
     */
    @Test
    void givenUserId_whenCreateFolder_successTest() throws Exception {
        FolderRequestDto folderRequestDto = new FolderRequestDto();
        folderRequestDto.setPrivacy(Privacy.PRIVATE);
        folderRequestDto.setDescription("test test test");
        folderRequestDto.setName("Test Name");

        mockMvc.perform(post("/api/user/folders/persons")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(101L))
                        .content(new ObjectMapper().writeValueAsString(folderRequestDto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/folders/persons")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(101L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)));

    }

    /**
     * ТЕСТ-КЕЙС
     * Успешное удаление папки
     */
    @Test
    void deleteFoldersById_successTest() throws Exception {
        mockMvc.perform(post("/api/user/folders/movies/{id}", 101L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/folders/persons")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(100L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)));
    }

    /**
     * ТЕСТ-КЕЙС
     * Неудачное удаление несуществующего фолдера
     */

    @Test
    void deleteFolderPersonById_failedTest() throws Exception {
        mockMvc.perform(post("/api/user/folders/persons/{id}", 999L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                .param("_method", "DELETE"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Папки с таким id = 999 не существует"));
    }
}