package com.revature.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.revature.util.AuthorityDeserializer;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User model that will keep user information, users own trips that consist of
 * waypoints. The username
 * and password are used for authentication
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
    private int userId;

    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Trip> trips;

    /**
     * Constructor for user objects used in testing, userId is auto-generated when
     * persistsed to database
     * 
     * @param username user name for the user
     * @param password password for the user
     * @param trips    a list of all the trips owned by the user
     */

    public User(String username, String password, List<Trip> trips) {
        this.username = username;
        this.password = password;
        this.trips = trips;
    }

    /**
     * Used to authenticate user when logging in
     * 
     * @return a list of the granted authorities given to a user when logged in
     */

    @Override
    @JsonDeserialize(using = AuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        return authorities;
    }

    /**
     * Methods used for authentication and secure different functionalities/data to
     * specific users
     */

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
