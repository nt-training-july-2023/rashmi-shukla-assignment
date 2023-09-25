package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.ResultDto;

/**
 * Service interface for managing question-related operations.
 */
public interface ResultService {
    /**
     * adds a new result.
     * @param resultDto of the result to be added.
     * @return String with message added success.
     */
    String addResult(ResultDto resultDto);

    /**
     * gets all results.
     * @return list of ResultDto
     */
    List<ResultDto> getAllResults();

    /**
     * gets result of a user by their email.
     * @param userEmail email of the user.
     * @return list of ResultDto for user.
     */
    List<ResultDto> getResultByUserEmail(String userEmail);
}
