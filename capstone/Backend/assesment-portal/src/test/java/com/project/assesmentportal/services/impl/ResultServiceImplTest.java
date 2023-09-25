package com.project.assesmentportal.services.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.project.assesmentportal.dto.ResultDto;
import com.project.assesmentportal.entities.Result;
import com.project.assesmentportal.repositories.ResultRepository;

class ResultServiceImplTest {

    @InjectMocks
    private ResultServiceImpl resultService;
    
    @Mock
    private ResultRepository resultRepository;
    
    @Mock
    private ModelMapper modelMapper;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddResult_Success() {
        ResultDto resultDto = new ResultDto();
        Result result = new Result();
        when(modelMapper.map(resultDto, Result.class)).thenReturn(result);
        when(resultRepository.save(result)).thenReturn(result);
        
        String output = resultService.addResult(resultDto);
        assertEquals("Result added successfully!", output);
    }
    
    @Test
    void testGetAllResults_Success() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result());
        when(resultRepository.findAll()).thenReturn(resultList);

        List<ResultDto> resultDtoList = new ArrayList<>();
        resultDtoList.add(new ResultDto());
        
        when(modelMapper.map(any(Result.class), ResultDto.class)).thenReturn(new ResultDto());

        List<ResultDto> actualResult = resultService.getAllResults();

        assertEquals(resultDtoList.size(), actualResult.size());
    }
    
    @Test 
    void GetResultByUserEmail_Success(){
        String userEmail = "rsh@gmail.com";
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result());
        when(resultRepository.findByUserEmail(userEmail)).thenReturn(resultList);

        List<ResultDto> resultDtoList = new ArrayList<>();
        resultDtoList.add(new ResultDto());
        when(modelMapper.map(any(Result.class), ResultDto.class)).thenReturn(new ResultDto());

        List<ResultDto> output = resultService.getResultByUserEmail(userEmail);

        assertEquals(resultDtoList.size(), output.size());
    }

}
