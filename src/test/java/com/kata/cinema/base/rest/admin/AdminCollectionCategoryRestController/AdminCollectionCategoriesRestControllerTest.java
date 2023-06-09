package com.kata.cinema.base.rest.admin.AdminCollectionCategoryRestController;

import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.CollectionCategoriesRequestDto;
import com.kata.cinema.base.rest.util.IntegrationTestingAccessTokenUtil;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCollectionCategoryRestController/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/db/scripts/rest/admin/AdminCollectionCategoryRestController/after.sql")
public class AdminCollectionCategoriesRestControllerTest extends SpringContextTest {


    // Получение списка категорий из базы данных
    @Test
    void whenGetCategories_thenReturnCollectionCategories_successTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);
        mockMvc.perform(get("/api/admin/collections/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[0].name", Is.is("Category 1")))
                .andExpect(jsonPath("$[1].id", Is.is(2)))
                .andExpect(jsonPath("$[1].name", Is.is("Category 2")))
                .andExpect(jsonPath("$[2].id", Is.is(3)))
                .andExpect(jsonPath("$[2].name", Is.is("Category 3")));

    }

    // Удаление категории по id из базы данных
    @Test
    void givenId_whenDeleteCollectionCategories_thenReturnVoid_successTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        mockMvc.perform(post("/api/admin/collections/categories/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/collections/categories/{id}", 3L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/admin/collections/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$[0].id", Is.is(2)));
    }

    // Обновление категории по id в базе данных
    @Test
    void givenId_whenUpdateCollectionCategories_thenReturnCollectionCategories_successTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        mockMvc.perform(post("/api/admin/collections/categories/{id}", 2L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "PUT")
                        .param("name", "New Category"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/collections/categories/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/collections/categories/{id}", 3L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/admin/collections/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$[0].id", Is.is(2)))
                .andExpect(jsonPath("$[0].name", Is.is("New Category")));
    }


    // Создание новой категории в базе данных
    @Test
    void whenCreateNewCollectionCategories_thenReturnVoid_successTest() throws Exception {
        String token = IntegrationTestingAccessTokenUtil.obtainNewAccessToken("adm@gmail.ru", "admin", mockMvc);

        CollectionCategoriesRequestDto requestDto = new CollectionCategoriesRequestDto();
        requestDto.setName("New Category");

        mockMvc.perform(post("/api/admin/collections/categories/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/collections/categories/{id}", 2L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/collections/categories/{id}", 3L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("_method", "DELETE"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/admin/collections/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/admin/collections/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$[0].name", Is.is("New Category")));
    }
}
