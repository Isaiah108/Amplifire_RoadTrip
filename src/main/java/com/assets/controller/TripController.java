package com.revature.controller;

import java.util.List;

import com.revature.model.Trip;
import com.revature.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling HTTP requests concerning trip objects, communicates
 * with TripService in order to persist
 * data to database
 */
@RestController()
@RequestMapping(value = "/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    /**
     * Responsible for taking post request from URI and calls trip service to create
     * a new trip
     * 
     * @param trip the trip object to be created and persisted
     * @return the trip if successfully persisted
     */
    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.createTrip(trip);
    }

    /**
     * Searches for and returns a trip object found on the database given a tripId
     * 
     * @param tripId the tripId of the trip that is requested for
     * @return the trip object if found in the database
     */
    @GetMapping("/{tripId}")
    public Trip getTripById(@PathVariable String tripId) {
        return tripService.getTripById(Integer.parseInt(tripId));
    }

    /**
     * Gathers all the trips in the database that belong to a particular user
     * 
     * @param userId the userId of all the trips being requested for
     * @return a list of the all the trips that belong to the requested userId
     */
    @GetMapping("/getAll/{userId}")
    public List<Trip> getAllTripsById(@PathVariable String userId) {
        return tripService.getAllTripsById(Integer.parseInt(userId));
    }

    /**
     * Updates a trip if it already exists in the database, otherwise creates a new
     * trip
     * 
     * @param trip the trip being requested for an update
     * @return the new trip object if it is successfully persisted to the database
     */
    @PutMapping
    public Trip updateTrip(@RequestBody Trip trip) {
        return tripService.updateTrip(trip);
    }

    /**
     * Takes in DELETE request from URI and deletes a trip if it exists in the
     * database using tripService
     * 
     * @param tripId the tripId of the trip
     * @return a boolean corresponding whether the deletion was successful or not
     */
    @DeleteMapping("/{tripId}")
    public boolean deleteTripById(@PathVariable String tripId) {
        return tripService.deleteTripById(Integer.parseInt(tripId));
    }
}
