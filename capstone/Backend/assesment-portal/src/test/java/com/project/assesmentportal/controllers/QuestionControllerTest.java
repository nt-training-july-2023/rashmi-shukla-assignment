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

import com.project.assesmentportal.dto.QuestionDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.entities.Options;
import com.project.assesmentportal.services.impl.QuestionServiceImpl;

class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;
    
    @Mock
    private QuestionServiceImpl questionService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddQuestion() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());
        when(questionService.addQuestion(questionDto)).thenReturn("Question added successfully!");
        ResponseEntity<String> response = questionController.addQuestion(questionDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Question added successfully!", response.getBody());
    }
    
    @Test
    public void testGetQuestions() {
        List<QuestionDto> questions = new ArrayList<>();
        QuizDto quizDto = new QuizDto(1,"Gk","quiz",20,null);
        questions.add(new QuestionDto(1L, "question",new Options("1","2","3","4"),"4", quizDto) );
        when(questionService.getAllQuestions()).thenReturn(questions);
        List<QuestionDto> result = questionController.getAllQuestions();
        assertEquals(1, result.size());
        assertEquals("question", result.get(0).getQuestionTitle());
    }
    
    @Test
    public void testGetQuiz() {
        long questionId = 1;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());
        
        when(questionService.getQuestionById(questionId)).thenReturn(questionDto);
        ResponseEntity<QuestionDto> result = questionController.getQuestionById(questionId);
        assertEquals(questionDto, result.getBody());
        assertEquals("2+2=?", result.getBody().getQuestionTitle());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void testUpdateQuestion() {
        long questionId = 1;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());
        
        when(questionService.updateQuestion(questionDto,questionId)).thenReturn("Category with id: 1 updated successfully!");
        ResponseEntity<String> result = questionController.updateQuestion(questionDto, questionId);
        assertEquals("Category with id: 1 updated successfully!", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void testDeleteQuestion() {
        long questionId = 1L;
        ResponseEntity<?> result = questionController.deleteQuestion(questionId);
        assertEquals("Question deleted successfully", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(questionService).deleteQuestion(questionId);
    }

}
