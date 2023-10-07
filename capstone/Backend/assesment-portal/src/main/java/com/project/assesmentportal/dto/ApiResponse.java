package com.project.assesmentportal.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.NoArgsConstructor;

/**
 * Represents the response format for API responses.
 */
@NoArgsConstructor
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
    private Map<String, String> errors;

    /**
     * Parameterized constructor for message and status.
     * @param responseMessage message.
     * @param responseStatus  status code.
     */
    public ApiResponse(final String responseMessage,
            final int responseStatus) {
        this.status = responseStatus;
        this.message = responseMessage;
    }

    /**
     * All args constructor for apiResponse.
     * @param responseMessage message.
     * @param responseStatus  status.
     * @param inputErrors     errors.
     */
    public ApiResponse(final String responseMessage,
            final int responseStatus,
            final Map<String, String> inputErrors) {
        this.status = responseStatus;
        this.message = responseMessage;
        this.errors =
                inputErrors != null ? new HashMap<>(inputErrors) : null;
    }

    /**
     * Getter for message.
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for message.
     * @param responseMessage message.
     */
    public void setMessage(final String responseMessage) {
        this.message = responseMessage;
    }

    /**
     * Getter method for status.
     * @return Status code.
     */
    public int getStatus() {
        return status;
    }

    /**
     * setter for Status.
     * @param responseStatus status.
     */
    public void setStatus(final int responseStatus) {
        this.status = responseStatus;
    }

    /**
     * Getter for errors.
     * @return validations error.
     */
    public Map<String, String> getErrors() {
        return errors != null ? Collections.unmodifiableMap(errors) : null;
    }

    /**
     * Setter for errors.
     * @param inputErrors validation errors.
     */
    public void setErrors(final Map<String, String> inputErrors) {
        this.errors =
                inputErrors != null ? new HashMap<>(inputErrors) : null;
    }

}
