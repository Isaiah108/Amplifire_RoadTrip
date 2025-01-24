package com.revature.model.exception;

/**
 * Definition for exception when a user inputs invalid credentials when registering or logging in
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(){
        super("Invalid credentials.");
    }
}
