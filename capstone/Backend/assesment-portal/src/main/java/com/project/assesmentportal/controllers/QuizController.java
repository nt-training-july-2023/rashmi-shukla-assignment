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

import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.services.QuizService;

/**
 * Controller class responsible for handling CRUD operations.
 */
@CrossOrigin("*")
@RestController
public class QuizController {

    /**
     * instance of quizService.
     */
    @Autowired
    private QuizService quizService;

    /**
     * handle add quiz.
     * @param quizDto quiz to be added.
     * @return added quiz.
     */
    @RequestMapping(value = "/quiz", method = RequestMethod.POST)
    public final ResponseEntity<QuizDto> addQuiz(
            @RequestBody final QuizDto quizDto) {
        QuizDto addQuizDto = this.quizService.addQuiz(quizDto);
        return new ResponseEntity<QuizDto>(addQuizDto, HttpStatus.CREATED);
    }

    /**
     * gets list of all quizzes.
     * @return list of quizzes.
     */
    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public final List<QuizDto> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    /**
     * gets quiz by id.
     * @param quizId id of quiz.
     * @return quizDto of quiz.
     */
    @RequestMapping(value = "/quiz/{id}", method = RequestMethod.GET)
    public final ResponseEntity<QuizDto> getQuizById(
            @PathVariable("id") final long quizId) {
        return new ResponseEntity<QuizDto>(quizService.getQuizById(quizId),
                HttpStatus.OK);
    }

    /**
     * updates quiz.
     * @param quizId  of quiz.
     * @param quizDto quiz to add.
     * @return updated QuizDto.
     */
    @RequestMapping(value = "/quiz/{id}", method = RequestMethod.PUT)
    public final ResponseEntity<QuizDto> updateQuiz(
            @PathVariable("id") final long quizId,
            @RequestBody final QuizDto quizDto) {
        return new ResponseEntity<QuizDto>(
                quizService.updateQuiz(quizDto, quizId), HttpStatus.OK);
    }

    /**
     * delete quiz.
     * @param quizId id of the quiz.
     * @return responseEntity.
     */
    @RequestMapping(value = "/quiz/{id}", method = RequestMethod.DELETE)
    public final ResponseEntity<String> deleteQuiz(
            @PathVariable("id") final long quizId) {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<String>("Quiz deleted successfully!",
                HttpStatus.OK);
    }

}
