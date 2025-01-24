package com.revature.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * Trip model for a trip that belongs to a user and consists of different
 * waypoints
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "trip_generator")
    @SequenceGenerator(name = "trip_generator", sequenceName = "trip_seq")
    private int tripId;

    @Column
    private String tripName;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Waypoint> waypoints;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    /**
     * Constructor for a trip, trip id is auto-generated and stored when persisted
     * to database
     *
     * @param tripName name of the trip
     * @param user     the user it belongs to
     */
    public Trip(String tripName, User user) {
        this.tripName = tripName;
        this.user = user;
    }
}

    

    
    
    
    
     
    
    

    

    