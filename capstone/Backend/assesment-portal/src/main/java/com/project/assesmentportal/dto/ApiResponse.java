package com.project.assesmentportal.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the response format for API responses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    /**
     * The message included in the API response.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * Indicates the status of the API response.
     */
    private int status;

    /**
     * Map of errors.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String,String> errors;
    
    public ApiResponse(String message, int status) {
        this.status = status;
        this.message = message;
    }
}
