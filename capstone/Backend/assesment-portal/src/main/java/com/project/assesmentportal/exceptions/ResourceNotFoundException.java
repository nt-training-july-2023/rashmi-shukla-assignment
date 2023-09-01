package com.project.assesmentportal.exceptions;

/**
 * Exception indicating that a requested resource was not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ResourceNotFoundException
     * with the specified detail message.
     * @param message The detail message explaining the cause of the exception.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
