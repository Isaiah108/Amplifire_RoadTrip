package com.revature.security.util;

import com.revature.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * this method essentially handles generating userDetails from a user. Userdetails is essentially potential
     * payload information when generating JWT. However we arent using any payload information at the moment.
     * @param username username to check against
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userdetails = userRepository.getUserByUsername(username);
        if (userdetails == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return userdetails;
    }
}
