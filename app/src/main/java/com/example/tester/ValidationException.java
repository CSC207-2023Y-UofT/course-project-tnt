package com.example.tester;

/**
 * A custom exception class used to represent validation errors.
 * This exception can be thrown when there is an issue with user data validation.
 */
public class ValidationException extends Exception {

    /**
     * Constructs a new {@code ValidationException} with the specified detail message.
     *
     * @param message The detail message, which provides information about the validation error.
     */
    public ValidationException(String message) {
        super(message);
    }
}