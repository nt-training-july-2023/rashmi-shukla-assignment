package com.project.assesmentportal.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.dto.QuestionDto;
import com.project.assesmentportal.services.QuestionService;

import jakarta.validation.Valid;

/**
 * Controller class responsible for handling CRUD operations.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/question")
public class QuestionController {

    /**
     * instance of questionService.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * The instance of Logger Class.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(QuestionController.class);

    /**
     * handle add question.
     * @param questionDto of question to be added.
     * @return added quiz.
     */
    @PostMapping()
    public final ResponseEntity<String> addQuestion(
            @RequestBody @Valid final QuestionDto questionDto) {
        LOGGER.info("Adding a question.");
        String addQuestion = this.questionService
                .addQuestion(questionDto);
        LOGGER.info("Added a question successfully.");
        return new ResponseEntity<String>(addQuestion,
                HttpStatus.CREATED);
    }

    /**
     * gets list of all questions.
     * @return list of questions.
     */
    @GetMapping()
    public final List<QuestionDto> getQuestions() {
        LOGGER.info("Retrieving a list of question.");
        List<QuestionDto> questionDtos =  questionService.getQuestions();
        LOGGER.info("Retrieved a list of question successfully.");
        return questionDtos;
    }

    /**
     * gets question by id.
     * @param questionId id of the question to be updated.
     * @return QuestionDto.
     */
    @GetMapping("/{id}")
    public final ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable("id") final long questionId) {
        LOGGER.info("Retrieving a question.");
        QuestionDto questionDto = questionService.getQuestionById(questionId);
        LOGGER.info("Retrieved a question successfully.");
        return new ResponseEntity<QuestionDto>(
                questionDto, HttpStatus.OK);
    }

    /**
     * updates question.
     * @param questionDto QuestionDto of new question.
     * @param questionId id of the question
     * @return QuestionDto.
     */
    @PutMapping("/{id}")
    public final ResponseEntity<String> updateQuestion(
            @RequestBody @Valid final QuestionDto questionDto,
            @PathVariable("id") final long questionId) {
        LOGGER.info("Updating a question with ID: " + questionId);
        String response = questionService.updateQuestion(questionDto, questionId);
        LOGGER.info("Updated a question with ID: " + questionId + " successfully.");
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    /**
     * deletes Question.
     * @param questionId id of the question to be deleted.
     * @return string
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<String> deleteQuestion(
            @PathVariable("id") final long questionId) {
        LOGGER.info("Deleting a question with ID: " + questionId);
        questionService.deleteQuestion(questionId);
        LOGGER.info("Deleted a question with ID: " + questionId + " successfully.");
        return new ResponseEntity<String>("Question deleted successfully",
                HttpStatus.OK);
    }
}
