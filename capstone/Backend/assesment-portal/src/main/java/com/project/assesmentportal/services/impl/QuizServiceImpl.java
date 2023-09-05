
package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.entities.Quiz;
import com.project.assesmentportal.repositories.QuizRepository;
import com.project.assesmentportal.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService{
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public final QuizDto addQuiz(final QuizDto quizDto) {
        Quiz quiz = this.modelMapper.map(quizDto, Quiz.class);
        
        Quiz savedQuiz = quizRepository.save(quiz);
        return this.modelMapper.map(savedQuiz, QuizDto.class);
    }

    @Override
    public final List<QuizDto> getAllQuizzes() {
        List<Quiz> quizzes= this.quizRepository.findAll();
        List<QuizDto> quizDtos = quizzes.stream()
                .map((quiz) -> this.modelMapper.map(quiz,
                        QuizDto.class))
                .collect(Collectors.toList());
        return quizDtos;
    }

    @Override
    public QuizDto getQuizById(long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        return this.modelMapper.map(quiz.get(), QuizDto.class);
    }

    @Override
    public QuizDto updateQuiz(QuizDto quizDto, long quizId) {
        Quiz exisitingQuiz = quizRepository.findById(quizId).get();
        exisitingQuiz.setQuizTitle(quizDto.getQuizTitle());
        exisitingQuiz.setQuizDescription(quizDto.getQuizDescription());
        exisitingQuiz.setQuizTimer(quizDto.getQuizTimer());
        
        Quiz updatedQuiz = quizRepository.save(exisitingQuiz);
        return this.modelMapper.map(updatedQuiz, QuizDto.class);

    }

    @Override
    public void deleteQuiz(long quizId) {
        quizRepository.deleteById(quizId);
    }

}
