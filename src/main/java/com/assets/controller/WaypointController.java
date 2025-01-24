package com.revature.controller;

import java.util.List;

import com.revature.model.Waypoint;
import com.revature.service.WaypointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for managing HTTP requests for waypoints, communicates
 * with waypoint service layer to persist
 * data in database
 */
@RestController()
@RequestMapping(value = "/waypoints")
public class WaypointController {

    @Autowired
    private WaypointService waypointService;

    /**
     * Handles a POST request and calls service layer to create a new waypoint and
     * persist to database
     * 
     * @param waypoint the waypoint to be created and persisted
     * @return the waypoint object if successfully persisted
     */
    @PostMapping
    public Waypoint createWaypoint(@RequestBody Waypoint waypoint) {
        return waypointService.createWaypoint(waypoint);
    }

    /**
     * Handles a GET request and calls service layer to search database for a
     * waypoint given a waypoint id
     * 
     * @param waypointId the waypoint id of the waypoint being searched for
     * @return the waypoint object that corresponds to the waypoint id if it exists
     */
    @GetMapping("/{waypointId}")
    public Waypoint getWaypointById(@PathVariable String waypointId) {
        return waypointService.getWaypointById(Integer.parseInt(waypointId));
    }

    /**
     * Handles a GET request and calls service layers, but instead searches for the
     * waypoints of a trip
     * 
     * @param tripId the trip id of the trip that will return all the waypoints
     *               belonging to the trip
     * @return a list of all the waypoints belonging to the trip
     */
    @GetMapping("/getAll/{tripId}")
    public List<Waypoint> getAllWaypointsById(@PathVariable String tripId) {
        return waypointService.getAllWaypointsById(Integer.parseInt(tripId));
    }

    /**
     * Handles a PUT request and calls service layer to update waypoints in the
     * database, creates new waypoint
     * it does not already exists
     * 
     * @param waypoint the waypoint object that will be updated
     * @return a waypoint object if it is successfully updated
     */
    @PutMapping
    public Waypoint updateWaypoint(@RequestBody Waypoint waypoint) {
        return waypointService.updateWaypoint(waypoint);
    }

    /**
     * Handles a DELETE request and calls service layer to delete a waypoint in the
     * database
     * 
     * @param waypointId waypoint id of the object in the database being deleted
     * @return a boolean corresponding to the success of the deletion
     */
    @DeleteMapping("/{waypointId}")
    public boolean deleteWaypointById(@PathVariable String waypointId) {
        return waypointService.deleteWaypointById(Integer.parseInt(waypointId));
    }
}
