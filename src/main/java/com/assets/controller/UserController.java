package com.revature.controller;

import com.revature.model.UserDTO;
import com.revature.service.AccountService;
import com.revature.model.UserDTO;
import com.revature.service.UserService;
import com.revature.security.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling users using URI requests and communicating with the
 * service layer
 */
@RestController()
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountService accountService;


    /**
     * Handles a GET request, retrieves a user from the database using service layer
     * and authenticating with a
     * generated token for security
     * 
     * @param incomingToken the authentication token to make sure user is logged in
     *                      successfully
     * @return the user object if authentication succeeds
     */
    @GetMapping
    public UserDTO getUserByToken(@RequestHeader("Authorization") String incomingToken) {
        String token = incomingToken.substring(7);
        return userService.getUserInformation(userService.getUserByUsername(jwtUtil.extractUsername(token)));
    }

    /**
     * Handles a PUT request and updates a user using the service layer
     * 
     * @param user the user to be updated if it exists
     * @return user object if successfully updated in the database
     */
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        return accountService.loginUser(userService.updateUser(userDTO));
    }

    /**
     * Handles a DELETE request and calls the service layer to delete the object
     * from the database by user id
     * 
     * @param userId the user id of the user requesting to be deleted
     * @return boolean corresponding to whether the deletion was successful
     */
    @DeleteMapping("{userId}")
    public boolean deleteUserById(@PathVariable String userId) {
        return userService.deleteUserById(Integer.parseInt(userId));
    }

}
