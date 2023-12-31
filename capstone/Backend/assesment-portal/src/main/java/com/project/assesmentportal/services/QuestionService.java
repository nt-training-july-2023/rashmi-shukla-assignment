package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.QuestionDto;

/**
 * Service interface for managing question-related operations.
 */
public interface QuestionService {
    /**
     * adds a new question.
     * @param questionDto of the question to be added.
     * @return QuestionDto
     */
    QuestionDto addQuestion(QuestionDto questionDto);

    /**
     * gets all questions.
     * @return all the questions.
     */
    List<QuestionDto> getAllQuestions();

    /**
     * updates an existing question.
     * @param questionDto updates question.
     * @param questionId  of the question to be updated.
     * @return updated question.
     */
    QuestionDto updateQuestion(QuestionDto questionDto, long questionId);

    /**
     * get question by question-id.
     * @param questionId of the question.
     * @return question.
     */
    QuestionDto getQuestionById(long questionId);

    /**
     * delete question by Id.
     * @param questionId id of the question to be deleted.
     */
    void deleteQuestion(long questionId);
}
