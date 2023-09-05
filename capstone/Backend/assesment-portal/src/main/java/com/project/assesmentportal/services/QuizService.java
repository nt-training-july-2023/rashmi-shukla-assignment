package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.QuizDto;

public interface QuizService {
    
    QuizDto addQuiz(QuizDto quizDto);
    
    List<QuizDto> getAllQuizzes();
    
    QuizDto getQuizById(long quizId);
    
    QuizDto updateQuiz(QuizDto quizDto, long quizId);
    
    void deleteQuiz(long quizId);
}
