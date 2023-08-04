package com.example.tester;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SigninValidationTest {
    private UserRepo mockDatabaseHelper;
    private SigninValidation signinValidation;

    @Before
    public void setUp() {
        mockDatabaseHelper = new MockDatabaseOperations();
        signinValidation = new SigninValidation();
    }

    @Test
    public void testValidateSignin_InvalidCredentials() {

        String validUsername = "testuser";
        String validPassword = "testpassword";

        ValidationException exception = assertThrows(ValidationException.class,
                () -> signinValidation.validateSignin(mockDatabaseHelper, validUsername, validPassword));
        assertEquals("Fill all the fields.", exception.getMessage());
    }

    @Test
    public void testValidateSignin_EmptyUsername() {
        String emptyUsername = "";
        String validPassword = "testpassword";

        ValidationException exception = assertThrows(ValidationException.class,
                () -> signinValidation.validateSignin(mockDatabaseHelper, emptyUsername, validPassword));
        assertEquals("Fill all the fields.", exception.getMessage());
    }

    @Test
    public void testValidateSignin_EmptyPassword() {
        String validUsername = "testuser";
        String emptyPassword = "";

        ValidationException exception = assertThrows(ValidationException.class,
                () -> signinValidation.validateSignin(mockDatabaseHelper, validUsername, emptyPassword));
        assertEquals("Fill all the fields.", exception.getMessage());
    }

    @Test
    public void testValidateSignin_Success() throws ValidationException {

        String validUsername = "testuser";
        String validPassword = "testpassword";

        signinValidation.validateSignin(mockDatabaseHelper, validUsername, validPassword);
    }
}
