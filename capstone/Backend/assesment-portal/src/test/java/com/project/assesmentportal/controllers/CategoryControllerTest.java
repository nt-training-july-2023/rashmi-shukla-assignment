package com.project.assesmentportal.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.services.impl.CategoryServiceImpl;

class CategoryControllerTest {
    
    @InjectMocks
    private CategoryController categoryController;
    
    @Mock
    private CategoryServiceImpl categoryService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetCategories() {
        List<CategoryDto> categories = new ArrayList<>();
        categories.add(new CategoryDto(1, "GK","GK Category"));
        categories.add(new CategoryDto(2, "Maths","Maths Category"));
        when(categoryService.getAllCategories()).thenReturn(categories);
        List<CategoryDto> result = categoryController.getAllCategories();
        assertEquals(2, result.size());
        assertEquals("GK", result.get(0).getCategoryTitle());
        assertEquals("Maths Category", result.get(1).getCategoryDescription());
    }
    
    @Test
    public void testAddCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryTitle("Maths");
        categoryDto.setCategoryDescription("Maths Category");
        when(categoryService.addCategory(categoryDto)).thenReturn("Category: "+categoryDto.getCategoryTitle()+", added successfully!");
        ResponseEntity<String> response = categoryController.addCategory(categoryDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Category: "+categoryDto.getCategoryTitle()+", added successfully!", response.getBody());
    }
    
    @Test
    public void testGetCategory() {
        long categoryId = 1;
        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setCategoryTitle("GK");
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDTO);
        ResponseEntity<CategoryDto> result = categoryController.getCategoryById(categoryId);
        assertEquals(categoryDTO, result.getBody());
        assertEquals("GK", result.getBody().getCategoryTitle());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void testUpdateCategory() {
        long categoryId = 1;
        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setCategoryTitle("Maths Category");
        when(categoryService.updateCategory(categoryDTO,categoryId)).thenReturn("Category: "+categoryDTO.getCategoryTitle()+", updated successfully!");
        ResponseEntity<String> result = categoryController.updateCategory(categoryId, categoryDTO);
        assertEquals("Category: "+categoryDTO.getCategoryTitle()+", updated successfully!", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void testDeleteCategory() {
        long categoryId = 1L;
        ResponseEntity<?> result = categoryController.deleteCategory(categoryId);
        assertEquals("Category deleted successfully!", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(categoryService).deleteCategory(categoryId);
    }

}
