package com.kata.cinema.base.webapp.controller.admin.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.SpringContextTest;
import com.kata.cinema.base.models.dto.request.CategoryRequestDto;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.facade.CollectionCategoryFacade;
import jakarta.persistence.EntityNotFoundException;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@AutoConfigureMockMvc
public class AdminCollectionCategoryRestControllerTest extends SpringContextTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CollectionCategoryFacade categoryFacade;

    @Test
    public void testGetCategories() throws Exception {
        List<CollectionCategories> categories = Arrays.asList(
                new CollectionCategories(1L, "Category 1"),
                new CollectionCategories(2L, "Category 2")
        );
        when(categoryFacade.getAllCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/collections/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].id", Is.is(1)))
                .andExpect(jsonPath("$.[0].name", Is.is("Category 1")))
                .andExpect(jsonPath("$.[1].id", Is.is(2)))
                .andExpect(jsonPath("$.[1].name", Is.is("Category 2")));
    }

    @Test
    public void testCreateCategory() throws Exception {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("New Category");

        ObjectMapper objectMapper = new ObjectMapper();
        String categoryRequestJson = objectMapper.writeValueAsString(categoryRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/collections/categories")
                        .content(categoryRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateCategoryWithValidationErrors() throws Exception {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("");

        ObjectMapper objectMapper = new ObjectMapper();
        String categoryRequestJson = objectMapper.writeValueAsString(categoryRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/collections/categories")
                        .content(categoryRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.message", Is.is("Validation failed")))
                .andExpect(jsonPath("$.errors[0].field", Is.is("name")))
                .andExpect(jsonPath("$.errors[0].message", Is.is("Category name cannot be blank")));
    }

    @Test
    public void testDeleteNonExistentCategory() throws Exception {
        Long categoryId = 1L;
        when(categoryFacade.getCategoryById(categoryId)).thenThrow(new EntityNotFoundException("Category with id " + categoryId + " not found"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/collections/categories/{id}", categoryId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void testGetCategoryById() throws Exception {
        CollectionCategories category = new CollectionCategories(1L, "Category");
        when(categoryFacade.getCategoryById(category.getId())).thenReturn(category);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/collections/categories/{id}", category.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.name", Is.is("Category")));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        Long categoryId = 1L;
        String categoryName = "New Category Name";
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto(categoryName);

        ObjectMapper objectMapper = new ObjectMapper();
        String categoryRequestJson = objectMapper.writeValueAsString(categoryRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/collections/categories/{id}", categoryId)
                        .content(categoryRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(categoryFacade).updateCategory(categoryId, categoryName);
    }

    @Test
    public void testUpdateCategoryWithValidationErrors() throws Exception {
        Long categoryId = 1L;
        String categoryName = "";
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto(categoryName);

        ObjectMapper objectMapper = new ObjectMapper();
        String categoryRequestJson = objectMapper.writeValueAsString(categoryRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/collections/categories/{id}", categoryId)
                        .content(categoryRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.message", Is.is("Validation failed")))
                .andExpect(jsonPath("$.errors[0].field", Is.is("name")))
                .andExpect(jsonPath("$.errors[0].message", Is.is("Category name cannot be blank")));

        verify(categoryFacade, never()).updateCategory(categoryId, categoryName);
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Long categoryId = 1L;
        CollectionCategories category = new CollectionCategories(categoryId, "Category");
        when(categoryFacade.getCategoryById(categoryId)).thenReturn(category);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/collections/categories/{id}", categoryId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(categoryFacade).deleteCategory(category);
    }

}