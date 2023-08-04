package com.example.tester;

public class SigninValidation {

    public void validateSigninFields(String username, String password) throws ValidationException {
        if (username.equals("") || password.equals("")) {
            throw new ValidationException("Fill all the fields.");
        }
    }
}

