package com.campussocialmedia.authenticationservice.Services;

import com.campussocialmedia.authenticationservice.Entities.AuthenticationRequest;
import com.campussocialmedia.authenticationservice.Entities.AuthenticationResponse;

public interface authServiceInterface {
    public AuthenticationResponse login(String userName, String password);

    public AuthenticationResponse signUp(AuthenticationRequest authenticationRequest);
}
