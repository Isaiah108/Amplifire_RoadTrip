package com.revature.service;

import com.revature.model.*;
import com.revature.repository.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Communicates with repository and controller layers to perform CRUD
 * functionality for Trip objects and
 * persist data
 */
@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    /**
     * Communicates with repository layer to create a new trip in the database
     * 
     * @param trip trip being created
     * @return trip if successfully created
     */
    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    /**
     * Communicates with repository layer to retrieve all trips owned by a user
     * 
     * @param userId userId of all trips being returned
     * @return List of trips belonging to a user with the user id
     */
    public List<Trip> getAllTripsById(Integer userId) {
        return tripRepository.findAllByUser_UserId(userId);
    }

    /**
     * Communicates with repository layer to retrieve a trip object using a trip id
     * 
     * @param tripId the trip id of the trip object being retrieved
     * @return the trip object if successfully found in database
     */
    public Trip getTripById(Integer tripId) {
        return tripRepository.getByTripId(tripId);
    }

    /**
     * Communicates with repository layer to update a trip to the database, creates
     * a new trip if does not exist
     * 
     * @param trip the trip that will be updated into the database
     * @return trip object if successfully updated
     */
    public Trip updateTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    /**
     * Communicates with repository layer to delete a trip from the database
     * 
     * @param tripId the trip id of the trip being deleted
     * @return boolean corresponding to success of deletion
     */
    public boolean deleteTripById(Integer tripId) {
        if (tripRepository.existsById(tripId)) {
            tripRepository.deleteById(tripId);
            return true;
        }
        return false;
    }

}
