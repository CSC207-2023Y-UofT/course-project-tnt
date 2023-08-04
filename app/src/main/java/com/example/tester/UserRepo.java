package com.example.tester;

public interface UserRepo {
    boolean addUser(UserRepo db, String username, String password);
    boolean checkUsername(String username);
    boolean checkPassword(String username, String password);
}
