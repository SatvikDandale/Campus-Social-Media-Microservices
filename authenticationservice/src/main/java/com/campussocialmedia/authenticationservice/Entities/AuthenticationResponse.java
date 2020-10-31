package com.campussocialmedia.authenticationservice.Entities;

import java.io.Serializable;

/*
Specifies the Authentication response object.
Currently we need only one field in the Authentication Response object which is the JWT token.
*/
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse [jwt=" + jwt + "]";
    }

}
