package com.example.tester.useCase;

import com.example.tester.exceptions.RegistrationException;

/**
 * The RegisterValidation class handles the validation of user registration inputs.
 * It checks the provided username, password, and repassword for validity and interacts with the database.
 */
public class RegisterValidation {

    /**
     * Default constructor for RegisterValidation.
     */
    public RegisterValidation() {
    }

    /**
     * Validates the provided registration inputs.
     *
     * @param myDB The UserRepo instance to interact with the database.
     * @param username The username to be validated.
     * @param password The password to be validated.
     * @param repassword The re-entered password to be validated.
     * @throws RegistrationException if the registration inputs are invalid or encounter database errors.
     */
    public void validateRegister(UserRepo myDB, String username, String password, String repassword) throws RegistrationException {
        if (username.equals("") || password.equals("") || repassword.equals("")) {
            throw new RegistrationException("Fill all the fields.");
        } else if (!password.equals(repassword)) {
            throw new RegistrationException("Password not Matching.");
        } else if (myDB.checkUsername(username)) {
            throw new RegistrationException("User already exists. \n Please Sign In.");
        } else {
            boolean regResult = myDB.addUser(myDB, username, password);
            if (!regResult) {
                throw new RegistrationException("Registration Failed.");
            }
        }
    }
}
