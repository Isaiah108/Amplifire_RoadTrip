package com.revature.service;

import com.revature.model.User;
import com.revature.model.exception.InvalidCredentialsException;
import com.revature.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // === CRUD methodology === //

    //CREATE
    public User createNewUser(User user) {
        //this 'if' may be extraneous entirely. instructor's code leaves this stmt out. Since unique = true, DB already takes care of it
        if (userRepository.existsByUsername(user.getUsername()))
            return null; //TODO perhaps throw exception???
        return userRepository.save(user);
    }

    //READ
    public User getUserById(Integer userId) {
        return userRepository.getUserByUserId(userId);
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    //UPDATE
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    //DELETE
    public boolean deleteUserById(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}