package com.revature.repository;

import com.revature.model.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that uses JpaRepository to access database using ORM functions, handles Waypoint table
 */
@Repository
public interface WaypointRepository extends JpaRepository<Waypoint, Integer> {
    Waypoint getByWaypointId(Integer waypointId);

    List<Waypoint> findAllByTrip_TripId(Integer tripId);
}
