package com.project.assesmentportal.controllers;

import java.util.List;

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
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.services.QuizService;

import jakarta.validation.Valid;

/**
 * Controller class responsible for handling CRUD operations.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/quiz")
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
    @PostMapping()
    public final ResponseEntity<String> addQuiz(
            @RequestBody @Valid final QuizDto quizDto) {
        return new ResponseEntity<String>(quizService.addQuiz(quizDto),
                HttpStatus.CREATED);
    }

    /**
     * gets list of all quizzes.
     * @return list of quizzes.
     */
    @GetMapping()
    public final List<QuizDto> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    /**
     * gets quiz by id.
     * @param quizId id of quiz.
     * @return quizDto of quiz.
     */
    @GetMapping("/{id}")
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
    @PutMapping("/{id}")
    public final ResponseEntity<String> updateQuiz(
            @PathVariable("id") final long quizId,
            @RequestBody @Valid final QuizDto quizDto) {
        return new ResponseEntity<String>(
                quizService.updateQuiz(quizDto, quizId), HttpStatus.OK);
    }

    /**
     * delete quiz.
     * @param quizId id of the quiz.
     * @return responseEntity.
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<String> deleteQuiz(
            @PathVariable("id") final long quizId) {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<String>("Quiz deleted successfully!",
                HttpStatus.OK);
    }

    /**
     * returns questions of a quiz.
     * @param id quizId.
     * @return list of questions ResponseEntity
     */
    @GetMapping("/{id}/questions")
    public final ResponseEntity<List<QuestionDto>> getQuestionsByQuiz(
            @PathVariable("id") final long id) {
        return new ResponseEntity<List<QuestionDto>>(
                quizService.getQuestionsByQuiz(id), HttpStatus.OK);
    }
}
