package com.project.assesmentportal.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.assesmentportal.dto.ApiResponse;

class ApiResponseTest {
    
    ApiResponse apiResponse;
    
    @BeforeEach
    void init() {
        apiResponse = new ApiResponse();
    }
    
    @Test
    void testGettersSetters() {
        assertEquals(null, apiResponse.getMessage());
        assertEquals(0, apiResponse.getStatus());
        
        
        apiResponse.setMessage("User not found");
        apiResponse.setStatus(404);
        
        assertEquals("User not found", apiResponse.getMessage());
        assertEquals(404, apiResponse.getStatus());
        
    }
    
    @Test
    void testDefaultConstructor() {
        ApiResponse apiResponse = new ApiResponse();
        assertEquals(null, apiResponse.getMessage());
        assertEquals(0, apiResponse.getStatus());
    }
    
    @Test
    void testParameterizedConstructor() {
        ApiResponse apiResponse = new ApiResponse("User not found", 404);
        assertEquals("User not found", apiResponse.getMessage());
        assertEquals(404, apiResponse.getStatus());
    }

}
