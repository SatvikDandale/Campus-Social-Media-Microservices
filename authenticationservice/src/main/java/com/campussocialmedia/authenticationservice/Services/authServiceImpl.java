package com.campussocialmedia.authenticationservice.Services;

import java.util.ArrayList;

import org.springframework.security.core.AuthenticationException;

import com.campussocialmedia.authenticationservice.Entities.AuthenticationRequest;
import com.campussocialmedia.authenticationservice.Entities.AuthenticationResponse;
import com.campussocialmedia.authenticationservice.Entities.UserDetailsResponse;
import com.campussocialmedia.authenticationservice.Proxies.UserServiceProxy;
import com.campussocialmedia.authenticationservice.Security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class authServiceImpl implements authServiceInterface {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceProxy userServiceProxy;

    @Autowired
    private JwtUtil jwtTokenUtil;

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

    @Override
    public AuthenticationResponse login(String userName, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        UserDetails userDetails = new User(userName, password, new ArrayList<>());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);

    }

    @Override
    public AuthenticationResponse signUp(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (AuthenticationException e) {
            ResponseEntity<UserDetailsResponse> response = userServiceProxy.saveUser(authenticationRequest);
            UserDetailsResponse user = response.getBody();
            UserDetails userDetails = new User(user.getUserName(), user.getPassword(), new ArrayList<>());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return new AuthenticationResponse(jwt);
        }
        return null;
    }

    @Override
    public String decodeJwt(String jwtToken) {
        return jwtTokenUtil.extractUsername(jwtToken);
    }

}
