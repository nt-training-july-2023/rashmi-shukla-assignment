package com.project.assesmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizDtoTest {
    
    QuizDto quizDto;
    
    @BeforeEach
    void init() {
        quizDto = new QuizDto();
    }
    
    @Test
    public void testGettersAndSetters() {
        // Set values using setters
        quizDto.setQuizId(1L);
        quizDto.setQuizTitle("Quiz");
        quizDto.setQuizDescription("Description");
        quizDto.setQuizTimer(20);

        // Verify values using getters
        assertEquals(1, quizDto.getQuizId());
        assertEquals("Quiz", quizDto.getQuizTitle());
        assertEquals("Description", quizDto.getQuizDescription());
        assertEquals(20, quizDto.getQuizTimer());
    }

    @Test
    public void testCategoryGetterAndSetter() {
        // Create a CategoryDto object
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1L);
        categoryDto.setCategoryTitle("Category 1");
        categoryDto.setCategoryDescription("Category description");

        // Set the category using the setter
        quizDto.setCategory(categoryDto);

        // Get the category using the getter
        CategoryDto retrievedCategoryDto = quizDto.getCategory();

        // Verify that the retrieved category is not null and has the expected values
        assertEquals(1L, retrievedCategoryDto.getCategoryId());
        assertEquals("Category 1", retrievedCategoryDto.getCategoryTitle());
        assertEquals("Category description", retrievedCategoryDto.getCategoryDescription());
    }
    
    @Test
    void testDefaultConstructor() {
        QuizDto quizDto = new QuizDto();
        assertEquals(0,quizDto.getQuizId());
        assertEquals(null, quizDto.getQuizTitle());
        assertEquals(null, quizDto.getQuizDescription());
        assertEquals(0, quizDto.getQuizTimer());
    }
    @Test
    void testParameterisedConstructor() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1L);
        categoryDto.setCategoryTitle("Category");
        categoryDto.setCategoryDescription("Category description");
        
        QuizDto quizDto = new QuizDto(1L,"Quiz","Descr",20,categoryDto);
        assertEquals(1, quizDto.getQuizId());
        assertEquals("Quiz", quizDto.getQuizTitle());
        assertEquals("Descr", quizDto.getQuizDescription());
        assertEquals(20,  quizDto.getQuizTimer());
//        assertEquals(categoryDto, quizDto.getCategory());
        
        CategoryDto retrievedDto = quizDto.getCategory();
        assertEquals(1L, retrievedDto.getCategoryId());
        assertEquals("Category", retrievedDto.getCategoryTitle());
        assertEquals("Category description", retrievedDto.getCategoryDescription());
    }

}
