package com.project.assesmentportal.exceptions;

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
    private String message;

    /**
     * Indicates the success status of the API response.
     */
    private boolean success;
}
