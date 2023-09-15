package com.project.assesmentportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.dto.QuestionDto;
import com.project.assesmentportal.services.QuestionService;

/**
 * Controller class responsible for handling CRUD operations.
 */
@CrossOrigin("*")
@RestController
public class QuestionController {

    /**
     * instance of questionService.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * handle add question.
     * @param questionDto of question to be added.
     * @return added quiz.
     */
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public final ResponseEntity<String> addQuestion(
            @RequestBody final QuestionDto questionDto) {
        String addQuestion = this.questionService
                .addQuestion(questionDto);
        return new ResponseEntity<String>(addQuestion,
                HttpStatus.CREATED);
    }

    /**
     * gets list of all questions.
     * @return list of questions.
     */
    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public final List<QuestionDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    /**
     * gets question by id.
     * @param questionId id of the question to be updated.
     * @return QuestionDto.
     */
    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public final ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable("id") final long questionId) {
        return new ResponseEntity<QuestionDto>(
                questionService.getQuestionById(questionId),
                HttpStatus.OK);
    }

    /**
     * updates question.
     * @param questionDto QuestionDto of new question.
     * @param questionId id of the question
     * @return QuestionDto.
     */
    @RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
    public final ResponseEntity<String> updateQuestion(
            @RequestBody final QuestionDto questionDto,
            @PathVariable("id") final long questionId) {
        return new ResponseEntity<String>(
                questionService.updateQuestion(questionDto, questionId),
                HttpStatus.OK);
    }

    /**
     * deletes Question.
     * @param questionId id of the question to be deleted.
     * @return string
     */
    @RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
    public final ResponseEntity<String> deleteQuestion(
            @PathVariable("id") final long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<String>("Question deleted successfully",
                HttpStatus.OK);
    }
}
