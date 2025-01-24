package com.revature.service;

import java.util.List;

import com.revature.model.Waypoint;
import com.revature.repository.WaypointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Communicates with repository and controller layers to perform CRUD
 * functionality for Waypoint objects and persist
 * data to database
 */
@Service
public class WaypointService {

    @Autowired
    private WaypointRepository waypointRepository;

    /**
     * Persists a new waypoint to the database by calling repository layer
     * 
     * @param waypoint waypoint being added to database
     * @return waypoint object if successfully added
     */
    public Waypoint createWaypoint(Waypoint waypoint) {
        return waypointRepository.save(waypoint);
    }

    /**
     * Retrieves all the waypoints of a trip
     * 
     * @param tripId the trip id of all the waypoints that will be returned
     * @return a list of waypoints that belong to the trip
     */
    public List<Waypoint> getAllWaypointsById(Integer tripId) {
        return waypointRepository.findAllByTrip_TripId(tripId);
    }

    /**
     * Retrieves a waypoint using the id of a waypoint
     * 
     * @param waypointId the waypoint id of a waypoint
     * @return the waypoint object if found in the database
     */
    public Waypoint getWaypointById(Integer waypointId) {
        return waypointRepository.getByWaypointId(waypointId);
    }

    /**
     * Updates a waypoint object in the database, creates a new waypoint if it does
     * not exist already
     * 
     * @param waypoint waypoint with the updated information
     * @return the waypoint object if successfully updated
     */
    public Waypoint updateWaypoint(Waypoint waypoint) {
        return waypointRepository.save(waypoint);
    }

    /**
     * Deletes a waypoint from the database
     * 
     * @param waypointId the waypoint id of the waypoint being deleted
     * @return boolean corresponding to the success of the deletion
     */
    public boolean deleteWaypointById(Integer waypointId) {
        if (waypointRepository.existsById(waypointId)) {
            waypointRepository.deleteById(waypointId);
            return true;
        }
        return false;
    }
}
