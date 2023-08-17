package com.example.tester.exceptions;

/**
 * Custom exception class for registration-related errors.
 * This exception is thrown when there is an issue with the registration process.
 */
public class RegistrationException extends Exception {

    /**
     * Constructs a new RegistrationException with the specified detail message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public RegistrationException(String message) {
        super(message);
    }
}
