package com.revature.service;

import com.revature.model.User;
import com.revature.model.exception.InvalidCredentialsException;
import com.revature.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.revature.security.model.AuthenticationResponse;
import com.revature.security.util.JwtUtil;
import com.revature.security.util.MyUserDetailsService;

/**
 * Communicates with repository layer and controller layer to register/login a
 * user, persists data to database
 */
@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    // TODO both these methods should eventually implement JWT response tokens and
    // ResponseEntity<?>

    /**
     * if the username doesn't already exist, add it into the database
     * 
     * @param user a user supplied by client side containing a new password and
     *             (potentially) new username
     * @return the newly registered user if it's a valid login
     */
    public User registerNewUser(User user) {
        if (user.getUsername() == null || user.getPassword() == null)
            throw new InvalidCredentialsException(); // user needs both a username and password to register

        if (userRepository.existsByUsername(user.getUsername()))
            throw new InvalidCredentialsException();// username collision
        else
            userRepository.save(user); // there wasn't a username collision, so we can save the new user.

        return userRepository.getUserByUsername(user.getUsername());// guaranteed since we saved it.
    }

    public ResponseEntity<?> loginUser(User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),
                            user.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Incorrect username or password!");
        }
        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
