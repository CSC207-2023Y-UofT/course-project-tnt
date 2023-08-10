package com.example.tester;

import com.example.tester.useCase.UserRepo;

/**
 * This class implements the UserRepo interface to provide mock database operations for testing purposes.
 * The methods in this class always return true to simulate successful operations.
 */
public class MockDatabaseOperations implements UserRepo {

    /**
     * Constructs a new MockDatabaseOperations instance.
     */
    MockDatabaseOperations(){
    }


    /**
     * Simulates adding a user to the mock database.
     *
     * @param db The UserRepo instance representing the database.
     * @param username The username of the user to be added.
     * @param password The password of the user to be added.
     * @return Always returns true to simulate a successful user addition.
     */
    @Override
    public boolean addUser(UserRepo db, String username, String password) {
        return true;
    }

    /**
     * Simulates checking if a username exists in the mock database.
     *
     * @param username The username to check for existence.
     * @return Always returns true to simulate the username being found.
     */
    @Override
    public boolean checkUsername(String username) {
        return true;
    }

    /**
     * Simulates checking if a given password matches the stored password for a username in the mock database.
     *
     * @param username The username for which to check the password.
     * @param password The password to be checked.
     * @return Always returns true to simulate the password being valid.
     */
    @Override
    public boolean checkPassword(String username, String password) {
        return true;
    }
}
