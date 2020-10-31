package com.campussocialmedia.authenticationservice.Controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.campussocialmedia.authenticationservice.Entities.AuthenticationRequest;
import com.campussocialmedia.authenticationservice.Entities.AuthenticationResponse;
import com.campussocialmedia.authenticationservice.Exceptions.ExceptionResponse;
import com.campussocialmedia.authenticationservice.Services.authServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
This class defines and implements the API endpoints for "/login" & "/signUp"
*/
@RestController
@CrossOrigin
public class authController {

    @Autowired
    private authServiceImpl authService;

    /*
     * AuthenticationManager passes the userName from incoming AuthenticationRequest
     * to MyUserDetailsService class to get the user object from the database as per
     * the incoming userName If no user is found with the incoming
     * AuthenticationRequest username, then AuthenticationException is thrown and
     * appropriate ResponseEntity is returned
     * 
     * MyUserDetailsService returns a User object containing the username and
     * password from the database. The password from the User object returned from
     * MyUserDetailsService is matched with the password in the incoming
     * AuthenticationRequest. If the password does not match, we throw a
     * BadCredentialsException and return the appropriate ResponseEntity for the
     * same
     * 
     * If the entire authentication by AuthenticationManager is successful, then we
     * generate the JWT token as per the user details and return the generated JWT
     * token and user as repsonse.
     */

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {

        System.out.println(authenticationRequest);
        AuthenticationResponse response = authService.login(authenticationRequest.getUserName(),
                authenticationRequest.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /*
     * The logic of signUp endpoint is to use AuthenticationManager to check if the
     * username in the incoming AuthenticationRequest exists and act accordingly.
     */
    @PostMapping(value = "/signUp")
    public ResponseEntity<?> signUp(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        // Try authenticating the given UserName and Password.
        // If no exception is thrown, then these credentials are already used.
        // If this UserName does not exists, then continue signUp. It will cause
        // AuthenticationException for unknown users.

        try {
            authService.login(authenticationRequest.getUserName(), authenticationRequest.getPassword());
        } catch (AuthenticationException e) {
            AuthenticationResponse response = authService.signUp(authenticationRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        // If no exception is returned by the AuthenticationManager, then the user with
        // passed
        // userName already exists.
        return new ResponseEntity<>(new ExceptionResponse(new Date(), "User Already Exists", "/signUp"),
                HttpStatus.CONFLICT);
    }

    /*
     * For authorization, other services might need to get the userName from tokens.
     */
    @PostMapping(value = "/jwt")
    public ResponseEntity<?> decodeJwt(@RequestBody Map<String, String> reqBody) {
        String userName = authService.decodeJwt(reqBody.get("token"));
        Map<String, String> response = new HashMap<String, String>();
        response.put("userName", userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
