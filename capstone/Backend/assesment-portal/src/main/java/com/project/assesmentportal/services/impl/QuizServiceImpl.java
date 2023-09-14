
package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.entities.Quiz;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.QuizRepository;
import com.project.assesmentportal.services.QuizService;

/**
 * Implementation of the QuizService interface for managing quiz-related
 * operations.
 */
@Service
public class QuizServiceImpl implements QuizService {

    /**
     * instance of ModelMapper.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * instance of QuizRepository interface.
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * Adds a new quiz.
     * @param quizDto The QuizDto representing the quiz to be added.
     * @return The QuizDto of the added category.
     * @throws DuplicateResourceException If a quiz with the same title
     *                                    already exists.
     */
    @Override
    public final QuizDto addQuiz(final QuizDto quizDto) {
        Quiz quiz = this.dtoToEntity(quizDto);
        Optional<Quiz> checkExistingQuiz = quizRepository
                .findByQuizTitle(quiz.getQuizTitle());
        if (checkExistingQuiz.isPresent()) {
            throw new DuplicateResourceException("Quiz already exists");
        }
        Quiz savedQuiz = quizRepository.save(quiz);
        return this.entityToDto(savedQuiz);
    }

    /**
     * Retrieves a list of all quizzes.
     * @return A list of QuizDto objects representing all quizzes.
     */
    @Override
    public final List<QuizDto> getAllQuizzes() {
        List<Quiz> quizzes = this.quizRepository.findAll();
        List<QuizDto> quizDtos = quizzes.stream()
                .map((quiz) -> this.entityToDto(quiz))
                .collect(Collectors.toList());
        return quizDtos;
    }

    /**
     * Retrieves a quiz by its ID.
     * @param quizId The ID of the quiz to retrieve.
     * @return The QuizDto of the retrieved quiz.
     * @throws ResourceNotFoundException If the quiz with the specified ID
     *                                   does not exist.
     */
    @Override
    public final QuizDto getQuizById(final long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            return this.entityToDto(quiz.get());
        } else {
            throw new ResourceNotFoundException("Quiz doesnot exists");
        }
    }

    /**
     * Updates a quiz.
     * @param quizDto The updated QuizDto.
     * @param quizId  The ID of the quiz to update.
     * @return The QuizDto of the updated category.
     * @throws ResourceNotFoundException If the quiz with the specified ID
     *                                   does not exist.
     */
    @Override
    public final QuizDto updateQuiz(final QuizDto quizDto,
            final long quizId) {
        Quiz exisitingQuiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Quiz doesnot exists"));

        // Check if the updated title is the same as the existing one
        if (!quizDto.getQuizTitle().equals(exisitingQuiz.getQuizTitle())) {
            // Title has changed, check for duplicates
            Optional<Quiz> checkExistingQuiz = quizRepository
                    .findByQuizTitle(quizDto.getQuizTitle());
            if (checkExistingQuiz.isPresent()) {
                throw new DuplicateResourceException(
                        "Quiz with the same title already exists");
            }
        }

        exisitingQuiz.setQuizTitle(quizDto.getQuizTitle());
        exisitingQuiz.setQuizDescription(quizDto.getQuizDescription());
        exisitingQuiz.setQuizTimer(quizDto.getQuizTimer());

        Category updatedCategory = modelMapper.map(quizDto.getCategory(),
                Category.class);
        exisitingQuiz.setCategory(updatedCategory);
        Quiz updatedQuiz = quizRepository.save(exisitingQuiz);
        return this.entityToDto(updatedQuiz);
    }

    /**
     * Deletes a quiz by its ID.
     * @param quizId The ID of the quiz to delete.
     * @throws ResourceNotFoundException If the quiz with the specified ID
     *                                   doesnot exist.
     */
    @Override
    public final void deleteQuiz(final long quizId) {
        quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Quiz doesnot exists"));
        quizRepository.deleteById(quizId);
    }

    /**
     * converts quiz to quizDto.
     * @param quizDto QuizDto to be converted.
     * @return quiz.
     */
    public final Quiz dtoToEntity(final QuizDto quizDto) {
        // Map the QuizDto to a Quiz entity
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        if (quizDto.getCategory() != null) {
            Category category = modelMapper.map(quizDto.getCategory(),
                    Category.class);
            quiz.setCategory(category);
        }
        return quiz;
    }

    /**
     * converts quizDto to quiz.
     * @param quiz Quiz to be converted.
     * @return quizDto.
     */
    public final QuizDto entityToDto(final Quiz quiz) {
        // Map the Quiz entity to a QuizDto
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        // Map the Category entity to a CategoryDto
        if (quiz.getCategory() != null) {
            CategoryDto categoryDto = modelMapper.map(quiz.getCategory(),
                    CategoryDto.class);
            quizDto.setCategory(categoryDto);
        }
        return quizDto;
    }

}
