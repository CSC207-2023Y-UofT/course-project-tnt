package com.example.tester;

/**
 * The SigninValidation class provides a method to validate user credentials during the sign-in process.
 * It checks whether the provided username and password are valid and meet certain criteria.
 */
public class SigninValidation {

    /**
     * Validates user credentials during sign-in.
     *
     * @param myDB The UserRepo instance to interact with the database.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @throws ValidationException If the provided username or password is empty, or if the credentials are invalid.
     */
    public void validateSignin(UserRepo myDB, String username, String password) throws ValidationException {
    if (username.equals("") || password.equals("")) {
        throw new ValidationException("Fill all the fields.");
    }
    boolean result = myDB.checkPassword(username, password);
    if (!result) {
        throw new ValidationException("Invalid credentials.");
    }
}
}
