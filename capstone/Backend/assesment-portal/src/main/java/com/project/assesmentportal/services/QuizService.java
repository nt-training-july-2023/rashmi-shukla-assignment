package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.QuizDto;

/**
 * Service interface for managing category-related operations.
 */
public interface QuizService {

    /**
     * Adds a new quiz.
     * @param quizDto The QuizDto representing the quiz to be added.
     * @return The QuizDto of the added quiz.
     */
    QuizDto addQuiz(QuizDto quizDto);

    /**
     * Retrieves a list of all quizzes.
     * @return A list of QuizDto objects representing all quizzes.
     */
    List<QuizDto> getAllQuizzes();

    /**
     * Retrieves quiz by id.
     * @param quizId of existing quiz.
     * @return QuizDto of specified quizId.
     */
    QuizDto getQuizById(long quizId);

    /**
     * Updates an existing quiz.
     * @param quizDto QuizDto of updated quiz.
     * @param quizId of existing quiz.
     * @return QuizDto of updated quiz.
     */
    QuizDto updateQuiz(QuizDto quizDto, long quizId);

    /**
     * delete quiz.
     * @param quizId of existing quiz.
     */
    void deleteQuiz(long quizId);
}
