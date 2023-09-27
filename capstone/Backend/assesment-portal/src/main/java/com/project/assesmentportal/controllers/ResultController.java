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

import com.project.assesmentportal.dto.ResultDto;
import com.project.assesmentportal.services.ResultService;

import jakarta.validation.Valid;

/**
 * Controller class responsible for handling CRUD operations.
 */
@CrossOrigin("*")
@RestController
public class ResultController {

    /**
     * instance of resultService.
     */
    @Autowired
    private ResultService resultService;

    /**
     * handles add result.
     * @param resultDto ResultDto to be added.
     * @return ResponseEntity<String>
     */
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public final ResponseEntity<String> addResult(
            @RequestBody @Valid final ResultDto resultDto) {
        String addResult = this.resultService.addResult(resultDto);
        return new ResponseEntity<String>(addResult, HttpStatus.CREATED);
    }

    /**
     * handles get all results.
     * @return List of all ResultDto.
     */
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public final List<ResultDto> getAllResults() {
        return resultService.getAllResults();
    }

    /**
     * handles get results by user email.
     * @param userEmail email of a user.
     * @return list of results for user with that email.
     */
    @RequestMapping(value = "/result/{userEmail}", method = RequestMethod.GET)
    public final List<ResultDto> getResultByUserEmail(
            @PathVariable final String userEmail) {
        return resultService.getResultByUserEmail(userEmail);
    }

}
