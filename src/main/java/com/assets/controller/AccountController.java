package com.revature.controller;

import com.revature.model.User;
import com.revature.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling HTTP requests concerning registering new accounts and
 * logging in to existing accounts,
 * uses AccountService in order to persist data
 */
@RestController()
@RequestMapping
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Parses register URI request and calls AccountService to register new user and
     * persist to database
     * 
     * @param user user to be registered
     * @return registered user if successful
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        return accountService.loginUser(accountService.registerNewUser(user));
    }

    /**
     * Takes in a login URI request and creates an authentication token for the
     * front-end session authentication
     * 
     * @param authenticationRequest the authentication request that will verify the
     *                              username and password
     * @return the authentication token to be used for verification in future
     *         requests
     * @throws Exception if username or password are not valid
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticateToken(@RequestBody User user) {
        return accountService.loginUser(user);
    }
}
