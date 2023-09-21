package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.ResultDto;

public interface ResultService {
    String addResult(ResultDto resultDto);
    
    List<ResultDto> getAllResults();
    
    List<ResultDto> getResultByUserEmail(String userEmail);
}
