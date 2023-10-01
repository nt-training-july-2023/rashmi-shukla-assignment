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

import com.project.assesmentportal.dto.ApiResponse;
import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.messages.MessageConstants;
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
        when(categoryService.getCategories()).thenReturn(categories);
        List<CategoryDto> result = categoryController.getCategories();
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
        
        ApiResponse apiResponse = new ApiResponse(MessageConstants.CATEGORY_ADDED_SUCCESSFULLY, HttpStatus.CREATED.value());
        when(categoryService.addCategory(categoryDto)).thenReturn(apiResponse);
        
        ResponseEntity<ApiResponse> response = categoryController.addCategory(categoryDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
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
        
        ApiResponse apiResponse = new ApiResponse(MessageConstants.CATEGORY_UPDATED_SUCCESSFULLY, HttpStatus.OK.value());
        when(categoryService.updateCategory(categoryDTO,categoryId)).thenReturn(apiResponse);
       
        ResponseEntity<ApiResponse> result = categoryController.updateCategory(categoryId, categoryDTO);
        assertEquals(apiResponse, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void testDeleteCategory() {
        long categoryId = 1L;
        
        ApiResponse apiResponse = new ApiResponse(MessageConstants.CATEGORY_DELETED_SUCCESSFULLY, HttpStatus.OK.value());
        when(categoryService.deleteCategory(categoryId)).thenReturn(apiResponse);
        
        ResponseEntity<ApiResponse> result = categoryController.deleteCategory(categoryId);
        assertEquals(apiResponse, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(categoryService).deleteCategory(categoryId);
    }
    
    @Test
    public void testGetQuizzesById(){
        long categoryId = 1L;
        List<QuizDto> quizList = new ArrayList<>();

        when(categoryService.getQuizzesByCategory(categoryId)).thenReturn(quizList);

        ResponseEntity<List<QuizDto>> response = categoryController.getQuizzesById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(quizList, response.getBody());
    }

}
