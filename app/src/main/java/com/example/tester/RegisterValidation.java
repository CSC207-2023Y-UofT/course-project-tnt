package com.example.tester;

public class RegisterValidation {
//    private UserRepo userRepo = new DatabaseHelper(this);

    RegisterValidation() {
    }

    public void validateRegister(DatabaseHelper myDB, String username, String password, String repassword) throws RegistrationException {
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
