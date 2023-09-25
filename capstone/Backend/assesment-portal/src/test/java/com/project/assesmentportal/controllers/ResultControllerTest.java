package com.project.assesmentportal.controllers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.assesmentportal.dto.ResultDto;
import com.project.assesmentportal.services.impl.ResultServiceImpl;

class ResultControllerTest {
    @InjectMocks
    private ResultController resultController;
    
    @Mock
    private ResultServiceImpl resultService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddResult() {
        ResultDto resultDto = new ResultDto();
        when(resultService.addResult(resultDto)).thenReturn("Result Added Successfully!");
        ResponseEntity<String> response = resultController.addResult(resultDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Result Added Successfully!", response.getBody());
    }
    
    @Test
    void testGetAllResults() {
        List<ResultDto> resultDtoList = new ArrayList<>();
        resultDtoList.add(new ResultDto());
        when(resultService.getAllResults()).thenReturn(resultDtoList);
        List<ResultDto> response = resultService.getAllResults();
        assertEquals(resultDtoList.size(), response.size());
    }
    
    @Test
    void TestGetResultByUserEmail() {
        String userEmail = "rsh@gmail.com";
        List<ResultDto> resultDtoList = new ArrayList<>();
        resultDtoList.add(new ResultDto());
        when(resultService.getResultByUserEmail(userEmail)).thenReturn(resultDtoList);
        List<ResultDto> response = resultService.getResultByUserEmail(userEmail);
        assertEquals(resultDtoList.size(), response.size());
    }

}
