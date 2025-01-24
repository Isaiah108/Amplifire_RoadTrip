package com.revature.repository;

import com.revature.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface that uses JpaRepository to access database using ORM functions, handles User table
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUserId(Integer userId);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    User getUserByUsername(String username);
}
