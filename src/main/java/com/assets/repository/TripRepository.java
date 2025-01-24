package com.revature.repository;

import com.revature.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface that uses JpaRepository to access database using ORM functions, handles Trip table
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    Trip getByTripId(Integer tripId);

    List<Trip> findAllByUser_UserId(Integer userId);
}