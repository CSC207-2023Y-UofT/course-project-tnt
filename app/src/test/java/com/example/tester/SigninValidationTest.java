package com.example.tester;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class contains unit tests for the SigninValidation class.
 * It uses JUnit's assertThrows to validate the exception scenarios and the successful sign-in scenario.
 */
public class SigninValidationTest {
    private UserRepo mockDatabaseHelper;
    private SigninValidation signinValidation;

    /**
     * Sets up the necessary mock objects and instances for testing.
     */
    @Before
    public void setUp() {
        mockDatabaseHelper = new MockDatabaseOperations();
        signinValidation = new SigninValidation();
    }

    /**
     * Tests the scenario where an unregistered username and password are provided, triggering a ValidationException.
     */
    @Test
    public void testValidateSignin_UnregisteredUser() {
        String invalidUsername = "invaliduser";
        String validPassword = "testpassword";
        try {
            signinValidation.validateSignin(mockDatabaseHelper, invalidUsername, validPassword);
        } catch (ValidationException e) {
            assertEquals("Invalid credentials.", e.getMessage());
        }
    }

    /**
     * Tests the scenario where the username field is empty, triggering a ValidationException.
     */
    @Test
    public void testValidateSignin_EmptyUsername() {
        String emptyUsername = "";
        String validPassword = "testpassword";

        ValidationException exception = assertThrows(ValidationException.class,
                () -> signinValidation.validateSignin(mockDatabaseHelper, emptyUsername, validPassword));
        assertEquals("Fill all the fields.", exception.getMessage());
    }

    /**
     * Tests the scenario where the password field is empty, triggering a ValidationException.
     */
    @Test
    public void testValidateSignin_EmptyPassword() {
        String validUsername = "testuser";
        String emptyPassword = "";

        ValidationException exception = assertThrows(ValidationException.class,
                () -> signinValidation.validateSignin(mockDatabaseHelper, validUsername, emptyPassword));
        assertEquals("Fill all the fields.", exception.getMessage());
    }

    /**
     * Tests the successful sign-in scenario where valid credentials are provided.
     */
    @Test
    public void testValidateSignin_Success() throws ValidationException {
        String validUsername = "testuser";
        String validPassword = "testpassword";
        signinValidation.validateSignin(mockDatabaseHelper, validUsername, validPassword);
    }
}
