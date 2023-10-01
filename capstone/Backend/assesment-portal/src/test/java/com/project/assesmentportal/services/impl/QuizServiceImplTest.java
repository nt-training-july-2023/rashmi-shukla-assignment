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
import org.springframework.http.HttpStatus;

import com.project.assesmentportal.dto.ApiResponse;
import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.dto.QuestionDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.entities.Question;
import com.project.assesmentportal.entities.Quiz;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.messages.MessageConstants;
import com.project.assesmentportal.repositories.CategoryRepository;
import com.project.assesmentportal.repositories.QuizRepository;

class QuizServiceImplTest {
    @InjectMocks
    private QuizServiceImpl quizServiceImpl;
    
    @Mock
    private QuizRepository quizRepository;
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private CategoryRepository categoryRepository;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    @Test
    void testAddQuiz_Success() {
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(1L);
        quizDto.setQuizTitle("Sample Quiz");
        
        Quiz quiz = new Quiz();
        quiz.setQuizId(1L);
        quiz.setQuizTitle("Sample Quiz");
        
        Category category = new Category();
        quiz.setCategory(category);
        
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        when(categoryRepository.findById(quiz.getCategory().getCategoryId())).thenReturn(Optional.of(category));
        when(quizRepository.findByQuizTitle(quiz.getQuizTitle())).thenReturn(Optional.empty());
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);
        
        ApiResponse result = quizServiceImpl.addQuiz(quizDto);
        assertNotNull(result);
        assertEquals(result.getMessage(), MessageConstants.QUIZ_ADDED_SUCCESSFULLY);
        assertEquals(HttpStatus.CREATED.value(), result.getStatus());
        
    }
    
    @Test
    void testAddQuiz_DuplicateQuiz() {
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizTitle("React");
        
        Quiz quiz = new Quiz();
        quiz.setQuizTitle("React");
        Category category = new Category();
        quiz.setCategory(category);
        
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        when(categoryRepository.findById(quiz.getCategory().getCategoryId())).thenReturn(Optional.of(category));
        when(quizRepository.findByQuizTitle(quiz.getQuizTitle())).thenReturn(Optional.of(quiz));
        
        assertThrows(DuplicateResourceException.class, ()-> {
            quizServiceImpl.addQuiz(quizDto);
        });
    }

    @Test
    void testAddQuiz_CategoryNotFound() {
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizTitle("React");
        
        Quiz quiz = new Quiz();
        quiz.setQuizTitle("React");
        Category category = new Category();
        quiz.setCategory(category);
        
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        when(categoryRepository.findById(quiz.getCategory().getCategoryId())).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, ()-> {
            quizServiceImpl.addQuiz(quizDto);
        });
    }
    
    @Test
    void testGetCategoryById_Success() {
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(1L);
        quizDto.setQuizTitle("React");
        
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizDto.getQuizId());
        quiz.setQuizTitle(quizDto.getQuizTitle());
        
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        when(quizRepository.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);
        
        QuizDto resultQuizDto = quizServiceImpl.getQuizById(1);
        assertNotNull(resultQuizDto);
        assertEquals(resultQuizDto.getQuizId(), quiz.getQuizId());
        assertEquals(resultQuizDto.getQuizTitle(), quiz.getQuizTitle());
    }
    
    @Test
    void testGetQuizById_QuizNotFound() {
        Quiz quiz = new Quiz();
        quiz.setQuizId(1);
        quiz.setQuizTitle("React");
        
        when(quizRepository.findById(quiz.getQuizId())).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            quizServiceImpl.getQuizById(1);
        });
    }
    
    @Test
    public void testGetQuizzes() {
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz(1, "React", "descr", 20, null));
        
        when(quizRepository.findAll()).thenReturn(quizList);
        List<QuizDto> quizDtos = quizServiceImpl.getQuizzes();
        
        assertNotNull(quizDtos);
        assertEquals(1, quizDtos.size());
    }
    
    @Test
    void testUpdateQuiz_Success() {
        long quizIdToUpdate = 1L;
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(1);
        quizDto.setQuizTitle("React");
        CategoryDto categoryDto = new CategoryDto();
        quizDto.setCategory(categoryDto);
        
        Category category = new Category();
        
        Quiz existingQuiz = new Quiz();
        existingQuiz.setQuizId(quizIdToUpdate);
        existingQuiz.setQuizTitle("Java");
        
        when(modelMapper.map(existingQuiz, QuizDto.class)).thenReturn(quizDto);
        when(quizRepository.findById(quizIdToUpdate)).thenReturn(Optional.of(existingQuiz));
        when(categoryRepository.findById(quizDto.getCategory().getCategoryId())).thenReturn(Optional.of(category));
        when(quizRepository.findByQuizTitle(quizDto.getQuizTitle())).thenReturn(Optional.empty());
        when(quizRepository.save(any(Quiz.class))).thenReturn(existingQuiz);
        
        ApiResponse result = quizServiceImpl.updateQuiz(quizDto,quizIdToUpdate);
        assertNotNull(result);
        assertEquals(result.getMessage(), MessageConstants.QUIZ_UPDATED_SUCCESSFULLY);
        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }
    
    @Test
    void testUpdateQuiz_ResourceNotFound() {
        long quizIdToUpdate = 1L;
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizTitle("React");
        quizDto.setQuizDescription("Mcqs");
        
        Quiz existingQuiz = new Quiz();
        existingQuiz.setQuizId(2L);
        existingQuiz.setQuizTitle("Java");
        existingQuiz.setQuizDescription("mcqs");
        
        when(quizRepository.findById(quizIdToUpdate)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            quizServiceImpl.updateQuiz(quizDto, quizIdToUpdate);
        });
        
    }
    
    @Test
    void testUpdateQuiz_DuplicateEmail() {
        //quiz to be updated
        long quizId = 1L;
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);
        quiz.setQuizTitle("React");

        // Existing quiz in the database
        Quiz existingQuiz = new Quiz();
        existingQuiz.setQuizId(2L);
        existingQuiz.setQuizTitle("Existing Quiz");

        // Updated QuizDto with a title that already exists
        QuizDto updatedQuizDto = new QuizDto();
        updatedQuizDto.setQuizId(1L);
        updatedQuizDto.setQuizTitle("Existing Quiz"); // Duplicate title
        CategoryDto categoryDto = new CategoryDto();
        updatedQuizDto.setCategory(categoryDto);

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(categoryRepository.findById(updatedQuizDto.getCategory().getCategoryId())).thenReturn(Optional.of(new Category()));
        when(quizRepository.findByQuizTitle(updatedQuizDto.getQuizTitle())).thenReturn(Optional.of(existingQuiz));

        assertThrows(DuplicateResourceException.class, () -> 
            quizServiceImpl.updateQuiz(updatedQuizDto, quizId));
        
    }
    
    @Test
    void testUpdateQuiz_CategoryNotFound() {
        //quiz to be updated
        long quizId = 1L;
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);
        quiz.setQuizTitle("React");

        // Existing quiz in the database
        Quiz existingQuiz = new Quiz();
        existingQuiz.setQuizId(2L);
        existingQuiz.setQuizTitle("Existing Quiz");

        // Updated QuizDto with a title that already exists
        QuizDto updatedQuizDto = new QuizDto();
        updatedQuizDto.setQuizId(1L);
        updatedQuizDto.setQuizTitle("Existing Quiz"); // Duplicate title
        CategoryDto categoryDto = new CategoryDto();
        updatedQuizDto.setCategory(categoryDto);

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(categoryRepository.findById(updatedQuizDto.getCategory().getCategoryId())).thenReturn(Optional.empty());
        when(quizRepository.findByQuizTitle(updatedQuizDto.getQuizTitle())).thenReturn(Optional.of(existingQuiz));

        assertThrows(ResourceNotFoundException.class, () -> 
            quizServiceImpl.updateQuiz(updatedQuizDto, quizId));
        
    }
    
    @Test
    public final void testDeleteQuiz_Success() {
        long quizIdToDelete = 1L;
        Quiz quizToDelete = new Quiz();
        quizToDelete.setQuizId(quizIdToDelete);

        when(quizRepository.findById(quizIdToDelete)).thenReturn(Optional.of(quizToDelete));

        ApiResponse response = quizServiceImpl.deleteQuiz(quizIdToDelete);
        verify(quizRepository, times(1)).deleteById(quizIdToDelete);
        assertEquals(response.getMessage(), MessageConstants.QUIZ_DELETED_SUCCESSFULLY);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
    @Test
    void testDeleteQuiz_NotFound() {
        long quizIdToDelete = 1L;

        // Mock the behavior of the repository to return an empty Optional
        when(quizRepository.findById(quizIdToDelete)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            quizServiceImpl.deleteQuiz(quizIdToDelete);
        });
    }
    
    @Test
    void testGetQuestionByQuiz_Success(){
        long quizId = 1L;
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);

        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        questions.add(question);
        quiz.setQuestions(questions);

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        when(modelMapper.map(question, QuestionDto.class)).thenReturn(new QuestionDto());

        List<QuestionDto> questionDtos = quizServiceImpl.getQuestionsByQuiz(quizId);

        assertNotNull(questionDtos);
        assertEquals(1, questionDtos.size());
    }
    
    @Test
    public void testGetQuestionsByQuizQuizNotFound() {
        long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            quizServiceImpl.getQuestionsByQuiz(quizId);
        });

        verify(quizRepository, times(1)).findById(quizId);
    }

}
