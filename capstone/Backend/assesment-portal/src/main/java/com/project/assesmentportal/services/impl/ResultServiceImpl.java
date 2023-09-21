package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.dto.ResultDto;
import com.project.assesmentportal.entities.Result;
import com.project.assesmentportal.repositories.ResultRepository;
import com.project.assesmentportal.services.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public String addResult(ResultDto resultDto) {
        Result result = this.modelMapper.map(resultDto, Result.class);
        resultRepository.save(result);
        return "Result added successfully!";
    }

    @Override
    public List<ResultDto> getAllResults() {
        List<Result> results = this.resultRepository.findAll();
        List<ResultDto> resultDtos = results.stream().map((result) -> 
            this.modelMapper.map(result, ResultDto.class)).collect(Collectors.toList());
        return resultDtos;
    }

    @Override
    public List<ResultDto> getResultByUserEmail(String userEmail) {
        List<Result> results = this.resultRepository.findByUserEmail(userEmail);
        List<ResultDto> resultDtos = results.stream().map((result) -> 
            this.modelMapper.map(result, ResultDto.class)).collect(Collectors.toList());
        return resultDtos;
    }

}
