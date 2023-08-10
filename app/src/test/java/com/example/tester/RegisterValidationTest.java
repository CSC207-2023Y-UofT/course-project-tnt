package com.example.tester;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.tester.exceptions.RegistrationException;
import com.example.tester.useCase.RegisterValidation;
import com.example.tester.useCase.UserRepo;

/**
 * This class contains unit tests for the RegisterValidation class.
 * It uses Mockito to mock the UserRepo interface and simulate database operations.
 */
public class RegisterValidationTest {

    private UserRepo mockDatabaseHelper;
    private RegisterValidation registerValidation;

    /**
     * Sets up the necessary mock objects and instances for testing.
     */
    @Before
    public void setUp() {
        mockDatabaseHelper = new MockDatabaseOperations();
        registerValidation = new RegisterValidation();
    }

    /**
     * Tests the successful registration scenario where the provided username does not exist
     * and the registration operation is expected to succeed.
     */
    @Test
    public void testValidateRegister_Success() {

        UserRepo mockUserRepo = mock(UserRepo.class);

        when(mockUserRepo.checkUsername("testuser")).thenReturn(false); // Username does not exist
        when(mockUserRepo.addUser(mockUserRepo, "testuser", "testpassword")).thenReturn(true); // User registration succeeds

        RegisterValidation registerUserValidation = new RegisterValidation();

        try {
            registerUserValidation.validateRegister(mockUserRepo, "testuser", "testpassword", "testpassword");
        } catch (RegistrationException e) {
            fail("Unexpected RegistrationException: " + e.getMessage());
        }
    }

    /**
     * Tests the scenario where the provided username already exists, triggering a RegistrationException.
     */
    @Test(expected = Exception.class)
    public void testValidateRegisterUser_UsernameExists() throws RegistrationException {
        registerValidation.validateRegister(mockDatabaseHelper, "existinguser", "testpassword", "tesetpassword");
        try {
            registerValidation.validateRegister(mockDatabaseHelper, "existinguser", "testpassword", "testpassword");
        } catch (RegistrationException e) {
            assertEquals("User already exists. \n Please Sign In.", e.getMessage());
        }
    }

    /**
     * Tests the scenario where the provided passwords do not match, triggering a RegistrationException.
     */
    @Test
    public void testValidateRegisterUser_PasswordNotMatching() {
        try {
            registerValidation.validateRegister(mockDatabaseHelper, "existinguser", "onepassword", "twopassword");
        } catch (RegistrationException e) {
            assertEquals("Password not Matching.", e.getMessage());
        }
    }

    /**
     * Tests the scenario where one of the required fields is empty, triggering a RegistrationException.
     */
    @Test
    public void testValidateRegisterUser_EmptyFields() {
        try {
            registerValidation.validateRegister(mockDatabaseHelper, "username", "testpassword", "");
        } catch (RegistrationException e) {
            assertEquals("Fill all the fields.", e.getMessage());
        }
    }

    /**
     * Tests the scenario where a database is not provided and UserRepo instance is null, expecting a NullPointerException to be thrown.
     */
    @Test
    public void testValidateRegisterUser_NullDatabaseHelper() {
        try {
            registerValidation.validateRegister(null, "testuser", "testpassword", "testpassword");
            fail("Expected NullPointerException, but no exception was thrown.");
        } catch (NullPointerException e) {
            // Test passed
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }
}
