package com.example.tester;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterValidationTest {

    private UserRepo mockDatabaseHelper;
    private RegisterValidation registerValidation;

    @Before
    public void setUp() {
        mockDatabaseHelper = new MockDatabaseOperations();
        registerValidation = new RegisterValidation();
    }

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

    @Test(expected = Exception.class)
    public void testValidateRegisterUser_UsernameExists() throws RegistrationException {
        registerValidation.validateRegister(mockDatabaseHelper, "existinguser", "testpassword", "tesetpassword");
        try {
            registerValidation.validateRegister(mockDatabaseHelper, "existinguser", "testpassword", "testpassword");
        } catch (RegistrationException e) {
            assertEquals("User already exists. \n Please Sign In.", e.getMessage());
        }
    }

    @Test
    public void testValidateRegisterUser_PasswordNotMatching() {
        try {
            registerValidation.validateRegister(mockDatabaseHelper, "existinguser", "onepassword", "twopassword");
        } catch (RegistrationException e) {
            assertEquals("Password not Matching.", e.getMessage());
        }
    }

    @Test
    public void testValidateRegisterUser_EmptyFields() {
        try {
            registerValidation.validateRegister(mockDatabaseHelper, "username", "testpassword", "");
        } catch (RegistrationException e) {
            assertEquals("Fill all the fields.", e.getMessage());
        }
    }

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