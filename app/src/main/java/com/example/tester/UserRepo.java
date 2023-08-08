package com.example.tester;

/**
 * Interface for interacting with user database operations.
 */
public interface UserRepo {
    /**
     * Adds a new user to the database.
     *
     * @param db The database instance to perform the operation on.
     * @param username The username of the user to be added.
     * @param password The password associated with the user.
     * @return {@code true} if the user was successfully added, {@code false} otherwise.
     */
    boolean addUser(UserRepo db, String username, String password);

    /**
     * Checks if a username already exists in the database.
     *
     * @param username The username to check.
     * @return {@code true} if the username already exists, {@code false} otherwise.
     */
    boolean checkUsername(String username);

    /**
     * Checks if the provided username and password match a user's credentials.
     *
     * @param username The username to check.
     * @param password The password to match.
     * @return {@code true} if the credentials are valid, {@code false} otherwise.
     */
    boolean checkPassword(String username, String password);
}
