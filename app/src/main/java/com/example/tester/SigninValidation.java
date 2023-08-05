package com.example.tester;

public class SigninValidation {    public void validateSignin(UserRepo myDB, String username, String password) throws ValidationException {
    if (username.equals("") || password.equals("")) {
        throw new ValidationException("Fill all the fields.");
    }
    boolean result = myDB.checkPassword(username, password);
    if (!result) {
        throw new ValidationException("Invalid credentials.");
    }
}
}
