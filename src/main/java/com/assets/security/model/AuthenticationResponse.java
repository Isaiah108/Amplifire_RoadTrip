package com.revature.security.model;

import lombok.*;

/**
 * Authentication response sent back if authentication succeeds, returns a json web token that is used to authenticate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}
