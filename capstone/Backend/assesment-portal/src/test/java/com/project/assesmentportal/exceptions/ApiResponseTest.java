package com.project.assesmentportal.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiResponseTest {
    
    ApiResponse apiResponse;
    
    @BeforeEach
    void init() {
        apiResponse = new ApiResponse();
    }
    
    @Test
    void testGettersSetters() {
        assertEquals(null, apiResponse.getMessage());
        assertEquals(false, apiResponse.isSuccess());
        
        
        apiResponse.setMessage("User doesnot exists");
        apiResponse.setSuccess(true);
        
        assertEquals("User doesnot exists", apiResponse.getMessage());
        assertEquals(true, apiResponse.isSuccess());
        
    }
    
    @Test
    void testDefaultConstructor() {
        ApiResponse apiResponse = new ApiResponse();
        assertEquals(null, apiResponse.getMessage());
        assertEquals(false, apiResponse.isSuccess());
    }
    
    @Test
    void testParameterizedConstructor() {
        ApiResponse apiResponse = new ApiResponse("User doesnot exists", true);
        assertEquals("User doesnot exists", apiResponse.getMessage());
        assertEquals(true, apiResponse.isSuccess());
    }

}
