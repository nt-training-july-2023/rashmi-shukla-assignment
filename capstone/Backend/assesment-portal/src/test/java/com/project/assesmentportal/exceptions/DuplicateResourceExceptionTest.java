package com.project.assesmentportal.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DuplicateResourceExceptionTest {

    @Test
    void testWrongCredentialsException() {
        String message = "Email-id already exists";
        DuplicateResourceException exception = new DuplicateResourceException(message);
        assertEquals(message, exception.getMessage());
    }

}
