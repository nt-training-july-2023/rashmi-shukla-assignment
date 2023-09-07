package com.project.assesmentportal.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizTest {
    Quiz quiz;
    
    @BeforeEach
    void init() {
        quiz = new Quiz();
    }

    @Test
    void testGettersAndSetters() {
        // Set values using setters
        quiz.setQuizId(1L);
        quiz.setQuizTitle("Sample Quiz");
        quiz.setQuizDescription("Sample description");
        quiz.setQuizTimer(30);

        // Verify values using getters
        assertEquals(1L, quiz.getQuizId());
        assertEquals("Sample Quiz", quiz.getQuizTitle());
        assertEquals("Sample description", quiz.getQuizDescription());
        assertEquals(30, quiz.getQuizTimer());

    }
    
    @Test
    public void testCategoryGetterAndSetter() {
        Category category = new Category(1L, "Category 1", "Category description");
        quiz.setCategory(category);
        Category retrievedCategory = quiz.getCategory();

        assertNotNull(retrievedCategory);
        assertEquals(1L, retrievedCategory.getCategoryId());
        assertEquals("Category 1", retrievedCategory.getCategoryTitle());
        assertEquals("Category description", retrievedCategory.getCategoryDescription());
    }

    @Test
    public void testConstructor() {
        Quiz newQuiz = new Quiz(2L, "Quiz 2", "description 2", 45);

        assertEquals(2L, newQuiz.getQuizId());
        assertEquals("Quiz 2", newQuiz.getQuizTitle());
        assertEquals("description 2", newQuiz.getQuizDescription());
        assertEquals(45, newQuiz.getQuizTimer());
    }
}
