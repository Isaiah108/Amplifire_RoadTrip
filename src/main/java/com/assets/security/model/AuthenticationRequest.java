package com.revature.security.model;

import lombok.*;

/**
 * Authentication request received used in security, uses a username and password to authenticate user
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest{

    private String username;
    private String password;
}
