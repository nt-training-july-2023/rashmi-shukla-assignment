package com.project.assesmentportal.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling various exceptions in the
 * application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the ResourceNotFoundException and creates an appropriate API
     * response.
     * @param ex The ResourceNotFoundException that was thrown.
     * @return A ResponseEntity with the corresponding ApiResponse.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(
            ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse,
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the DuplicateResourceException and creates an appropriate
     * API response.
     * @param ex The DuplicateResourceException that was thrown.
     * @return A ResponseEntity with the corresponding ApiResponse.
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse> duplicateResourceExceptionHandler(
            DuplicateResourceException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse,
                HttpStatus.CONFLICT);
    }

    /**
     * Handles the MethodArgumentNotValidException and creates a response
     * with validation error details.
     * @param ex The MethodArgumentNotValidException that was thrown.
     * @return A ResponseEntity with a map containing field names and
     *         validation error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(resp,
                HttpStatus.BAD_REQUEST);
    }
}
