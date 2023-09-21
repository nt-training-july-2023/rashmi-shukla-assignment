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

@CrossOrigin("*")
@RestController
public class ResultController {

    @Autowired
    private ResultService resultService;
    
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public final ResponseEntity<String> addResult(
            @RequestBody final ResultDto resultDto){
        String addResult = this.resultService.addResult(resultDto);
        return new ResponseEntity<String>(addResult, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public final List<ResultDto> getAllResults(){
        return resultService.getAllResults();
    }
    
    @RequestMapping(value = "/result/{userEmail}", method = RequestMethod.GET)
    public final List<ResultDto> getResultById(@PathVariable final String userEmail) {
        return resultService.getResultByUserEmail(userEmail);
    }
    
}
