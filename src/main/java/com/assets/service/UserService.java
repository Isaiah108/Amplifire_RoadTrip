package com.revature.service;

import com.revature.model.User;
import com.revature.model.UserDTO;
import com.revature.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Communicates with repository and controller layers to perform CRUD
 * functionality for User objects and persist data
 * to database
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Persists a new user to the database by calling the repository layer
     * 
     * @param user user being persisted to database
     * @return user object if it is successfully created
     */
    public User createNewUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            return null;
        return userRepository.save(user);
    }

    /**
     * Gets a user object with a userId
     * 
     * @param userId user id of object being retrieved
     * @return user object if found in database
     */
    public User getUserById(Integer userId) {
        return userRepository.getUserByUserId(userId);
    }

    /**
     * Retrieve a user object using the username as an identifier
     * 
     * @param username the username of the user object being retrieved
     * @return the user object if it is found in the database
     */
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    /**
     * Retrieve a user object with sensitive infromation redacted
     * feature-update-login
     *
     * @param user the user whose infromation needs to be redacted
     * @return the user with sensitive infromation redacted
     */
    public UserDTO getUserInformation(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setUserId(user.getUserId());

        return userDTO;
    }

    /**
     * Updates a user object in the database, creates a new user object if it does
     * not exists yet
     * 
     * @param user user object being updated
     * @return the user object if successfully updated
     */
    public User updateUser(UserDTO userDTO) {
        User user = userRepository.getUserByUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    /**
     * Delete a user in the database by calling user repository
     * 
     * @param userId user id of user being deleted
     * @return boolean corresponding to success of deletion
     */
    public boolean deleteUserById(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
