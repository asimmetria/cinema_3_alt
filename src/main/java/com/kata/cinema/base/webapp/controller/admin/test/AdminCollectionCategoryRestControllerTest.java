package com.kata.cinema.base.webapp.controller.admin.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.kata.cinema.base.models.dto.request.CategoryRequestDto;
import com.kata.cinema.base.models.dto.response.CategoryResponseDto;
import com.kata.cinema.base.models.dto.validator.CategoryRequestDtoValidator;
import com.kata.cinema.base.models.entitys.CollectionCategories;
import com.kata.cinema.base.service.facade.CollectionCategoryFacade;
import com.kata.cinema.base.webapp.controller.admin.AdminCollectionCategoryRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class AdminCollectionCategoryRestControllerTest {

    @Mock
    private CollectionCategoryFacade categoryFacade;

    @Mock
    private CategoryRequestDtoValidator categoryRequestDtoValidator;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AdminCollectionCategoryRestController adminCollectionCategoryRestController;

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "TestCategory";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategories() {
        List<CollectionCategories> categories = new ArrayList<>();
        CollectionCategories category = new CollectionCategories();
        category.setName(CATEGORY_NAME);
        categories.add(category);

        when(categoryFacade.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<CategoryResponseDto>> response = adminCollectionCategoryRestController.getCategories();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().size());
        Assertions.assertEquals(CATEGORY_NAME, response.getBody().get(0).getName());
    }

    @Test
    public void testDeleteCategory() {
        CollectionCategories category = new CollectionCategories();
        category.setId(CATEGORY_ID);
        category.setName(CATEGORY_NAME);

        when(categoryFacade.getCategoryById(CATEGORY_ID)).thenReturn(category);

        ResponseEntity<Void> response = adminCollectionCategoryRestController.deleteCategory(CATEGORY_ID);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryFacade, times(1)).deleteCategory(category);
    }

    @Test
    public void testUpdateCategory() {
        CollectionCategories category = new CollectionCategories();
        category.setId(CATEGORY_ID);
        category.setName(CATEGORY_NAME);

        when(categoryFacade.getCategoryById(CATEGORY_ID)).thenReturn(category);

        ResponseEntity<Void> response = adminCollectionCategoryRestController.updateCategory(CATEGORY_ID, CATEGORY_NAME);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertEquals(CATEGORY_NAME, category.getName());
        verify(categoryFacade, times(1)).updateCategory(category);
    }

    @Test
    public void testCreateCategory() {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName(CATEGORY_NAME);

        ResponseEntity<Void> response = adminCollectionCategoryRestController.createCategory(categoryRequestDto, bindingResult);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(categoryFacade, times(1)).createCategory(categoryRequestDto.getName());
    }
}