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
import com.project.assesmentportal.dto.QuestionDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.services.impl.QuizServiceImpl;

class QuizControllerTest {

    @InjectMocks
    private QuizController quizController;
    
    @Mock
    private QuizServiceImpl quizService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetQuizzes() {
        List<QuizDto> quizzes = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto(1,"Gk","category");
        quizzes.add(new QuizDto(1L, "GK","GK Quiz", 20, categoryDto));
        quizzes.add(new QuizDto(2L, "Maths","Maths Quiz",30, categoryDto));
        when(quizService.getAllQuizzes()).thenReturn(quizzes);
        List<QuizDto> result = quizController.getAllQuizzes();
        assertEquals(2, result.size());
        assertEquals("GK", result.get(0).getQuizTitle());
        assertEquals("Maths Quiz", result.get(1).getQuizDescription());
    }
    
    @Test
    public void testAddQuiz() {
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(1);
        quizDto.setQuizTitle("Maths");
        quizDto.setQuizDescription("Maths Category");
        when(quizService.addQuiz(quizDto)).thenReturn("Quiz: "+quizDto.getQuizTitle()+", added successfully!");
        ResponseEntity<String> response = quizController.addQuiz(quizDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Quiz: "+quizDto.getQuizTitle()+", added successfully!", response.getBody());
    }
    
    @Test
    public void testGetQuiz() {
        long quizId = 1;
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(quizId);
        quizDto.setQuizTitle("React");
        when(quizService.getQuizById(quizId)).thenReturn(quizDto);
        ResponseEntity<QuizDto> result = quizController.getQuizById(quizId);
        assertEquals(quizDto, result.getBody());
        assertEquals("React", result.getBody().getQuizTitle());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void testUpdateQuiz() {
        long quizId = 1;
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(quizId);
        quizDto.setQuizTitle("Aptitude Quiz");
        when(quizService.updateQuiz(quizDto,quizId)).thenReturn("Quiz: "+quizDto.getQuizTitle()+", updated successfully!");
        ResponseEntity<String> result = quizController.updateQuiz(quizId, quizDto);
        assertEquals("Quiz: "+quizDto.getQuizTitle()+", updated successfully!", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void testDeleteQuiz() {
        long quizId = 1L;
        ResponseEntity<?> result = quizController.deleteQuiz(quizId);
        assertEquals("Quiz deleted successfully!", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(quizService).deleteQuiz(quizId);
    }
    
    @Test
    public void testGetQuestionsByQuiz() {
        long quizId = 1L;
        List<QuestionDto> questionList = new ArrayList<>();

        when(quizService.getQuestionsByQuiz(quizId)).thenReturn(questionList);
        ResponseEntity<List<QuestionDto>> response = quizController.getQuestionsByQuiz(quizId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questionList, response.getBody());
    }


}
