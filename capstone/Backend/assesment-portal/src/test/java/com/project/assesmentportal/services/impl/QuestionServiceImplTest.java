package com.project.assesmentportal.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.project.assesmentportal.dto.QuestionDto;
import com.project.assesmentportal.entities.Options;
import com.project.assesmentportal.entities.Question;
import com.project.assesmentportal.entities.Quiz;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.QuestionRepository;
import com.project.assesmentportal.repositories.QuizRepository;

class QuestionServiceImplTest {

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
    private QuestionRepository questionRepository;
    
    @Mock
    private QuizRepository quizRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddQuestion_Success() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());

        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTitle(questionDto.getQuestionTitle());
        question.setOptionOne(questionDto.getOptions().getOptionI());
        question.setOptionTwo(questionDto.getOptions().getOptionII());
        question.setOptionThree(questionDto.getOptions().getOptionIII());
        question.setOptionFour(questionDto.getOptions().getOptionIV());
        question.setAnswer(questionDto.getAnswer());
        question.setQuiz(new Quiz());
        
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        when(quizRepository.findById(question.getQuiz().getQuizId())).thenReturn(Optional.of(new Quiz()));
        when(questionRepository.save(question)).thenReturn(question);
        
        String result = questionService.addQuestion(questionDto);
        assertEquals("Question added successfully!", result);
    }
    
    @Test
    void testAddQuestion_QuizNotFound() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());

        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTitle(questionDto.getQuestionTitle());
        question.setOptionOne(questionDto.getOptions().getOptionI());
        question.setOptionTwo(questionDto.getOptions().getOptionII());
        question.setOptionThree(questionDto.getOptions().getOptionIII());
        question.setOptionFour(questionDto.getOptions().getOptionIV());
        question.setAnswer(questionDto.getAnswer());
        question.setQuiz(new Quiz());
        
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        when(quizRepository.findById(question.getQuiz().getQuizId())).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, ()-> {
            questionService.addQuestion(questionDto);
        });
    }
    
    @Test
    void testgetAllQuestions_Success() {
        Question question = new Question(1, "2+2?", "1", "2", "3", "4", "4");
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        
        QuestionDto questionDto = new QuestionDto(1, "2+2?", new Options("1", "2", "3", "4"), "4", null);

        when(questionRepository.findAll()).thenReturn(questions);
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);

        List<QuestionDto> questionDtos = questionService.getAllQuestions();

        assertNotNull(questionDtos);
        assertEquals(1, questionDtos.size());
    }
    
    @Test
    void testUpdateQuestion_Success() {
        long questionIdToUpdate = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());

        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTitle(questionDto.getQuestionTitle());
        question.setOptionOne(questionDto.getOptions().getOptionI());
        question.setOptionTwo(questionDto.getOptions().getOptionII());
        question.setOptionThree(questionDto.getOptions().getOptionIII());
        question.setOptionFour(questionDto.getOptions().getOptionIV());
        question.setAnswer(questionDto.getAnswer());
        question.setQuiz(new Quiz());
        
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(quizRepository.findById(question.getQuiz().getQuizId())).thenReturn(Optional.of(new Quiz()));
        when(questionRepository.findById(questionIdToUpdate)).thenReturn(Optional.of(question));
        when(questionRepository.save(question)).thenReturn(question);
        
        String result = questionService.updateQuestion(questionDto, questionIdToUpdate);
        assertEquals("Question with id: 1 updated successfully!", result);
    }
    
    @Test
    void testUpdateQuiz_QuestionNotFound() {
        long questionIdToUpdate = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());

        Question question = new Question();
        question.setQuestionId(2);
        question.setQuestionTitle(questionDto.getQuestionTitle());
        question.setOptionOne(questionDto.getOptions().getOptionI());
        question.setOptionTwo(questionDto.getOptions().getOptionII());
        question.setOptionThree(questionDto.getOptions().getOptionIII());
        question.setOptionFour(questionDto.getOptions().getOptionIV());
        question.setAnswer(questionDto.getAnswer());
        
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(questionRepository.findById(questionIdToUpdate)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.updateQuestion(questionDto, questionIdToUpdate);
        });
    }
    
    @Test
    void testUpdateQuestion_QuizNotFound() {
        long questionIdToUpdate = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());

        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTitle(questionDto.getQuestionTitle());
        question.setOptionOne(questionDto.getOptions().getOptionI());
        question.setOptionTwo(questionDto.getOptions().getOptionII());
        question.setOptionThree(questionDto.getOptions().getOptionIII());
        question.setOptionFour(questionDto.getOptions().getOptionIV());
        question.setAnswer(questionDto.getAnswer());
        question.setQuiz(new Quiz());
        
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(quizRepository.findById(question.getQuiz().getQuizId())).thenReturn(Optional.empty());
        when(questionRepository.findById(questionIdToUpdate)).thenReturn(Optional.of(question));
        
        assertThrows(ResourceNotFoundException.class, ()-> {
            questionService.updateQuestion(questionDto, questionIdToUpdate);
        });
    }
    
    @Test
    void testGetQuestionById_Success() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());

        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTitle(questionDto.getQuestionTitle());
        question.setOptionOne(questionDto.getOptions().getOptionI());
        question.setOptionTwo(questionDto.getOptions().getOptionII());
        question.setOptionThree(questionDto.getOptions().getOptionIII());
        question.setOptionFour(questionDto.getOptions().getOptionIV());
        question.setAnswer(questionDto.getAnswer());
        
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        when(questionRepository.findById(question.getQuestionId())).thenReturn(Optional.of(question));
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        
        QuestionDto resultQuestionDto = questionService.getQuestionById(1);
        assertNotNull(resultQuestionDto);
    }
    
    @Test
    void testGetQuestionById_QuestionNotFound() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionTitle("2+2=?");
        Options options = new Options("2", "4", "5", "6");
        questionDto.setOptions(options);
        questionDto.setAnswer(options.getOptionII());

        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionTitle(questionDto.getQuestionTitle());
        question.setOptionOne(questionDto.getOptions().getOptionI());
        question.setOptionTwo(questionDto.getOptions().getOptionII());
        question.setOptionThree(questionDto.getOptions().getOptionIII());
        question.setOptionFour(questionDto.getOptions().getOptionIV());
        question.setAnswer(questionDto.getAnswer());
        
        when(questionRepository.findById(question.getQuestionId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.getQuestionById(1);
        });
    }
    
    @Test
    void testDeleteQuestion_Success(){
        long questionIdToDelete = 1;
        when(questionRepository.findById(questionIdToDelete)).thenReturn(Optional.of(new Question()));
        
        questionService.deleteQuestion(questionIdToDelete);
        verify(questionRepository, times(1)).deleteById(questionIdToDelete);
    }
    
    @Test
    void testDeleteQuestion_QuestionNotFound() {
        long questionIdToDelete = 1;
        when(questionRepository.findById(questionIdToDelete)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()->{
            questionService.deleteQuestion(questionIdToDelete);
        });
    }

}
