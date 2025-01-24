package com.revature.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

/**
 * Waypoint model that will store waypoint information, waypoints belong to trips which in turn belong to users
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "waypoints")
public class Waypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "waypoint_generator")
    @SequenceGenerator(name = "waypoint_generator", sequenceName = "waypoint_seq")
    private int waypointId;

    @Column
    private String waypointName;

    @Column(nullable = false)
    private String placeId;

    @ManyToOne()
    @JoinColumn(name = "trip_id", nullable = false)
    @JsonBackReference
    private Trip trip;

    /**
     * Constructor for waypoint object used in testing
     * @param waypointName name of the waypoint
     * @param longitude longitude of the waypoint
     * @param latitude latitude of the waypoint
     * @param trip trip to which the waypoint belongs to
     */
    public Waypoint(String waypointName, String placeId, Trip trip) {
        this.waypointName = waypointName;
        this.placeId = placeId;
        this.trip = trip;
    }
}
