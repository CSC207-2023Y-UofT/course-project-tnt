package com.example.tester;

public class MockDatabaseOperations implements UserRepo {

    MockDatabaseOperations(){

    }


    @Override
    public boolean addUser(UserRepo db, String username, String password) {

        return true;
    }

    @Override
    public boolean checkUsername(String username) {
        return true;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return true;
    }
}
