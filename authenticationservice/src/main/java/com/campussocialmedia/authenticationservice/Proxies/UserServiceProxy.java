package com.campussocialmedia.authenticationservice.Proxies;

import com.campussocialmedia.authenticationservice.Entities.AuthenticationRequest;
import com.campussocialmedia.authenticationservice.Entities.UserDetailsResponse;

import org.springframework.cloud.openfeign.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceProxy {
    @GetMapping("/userDetails/{userName}")
    public ResponseEntity<UserDetailsResponse> findUserDetails(@PathVariable("userName") String userName);

    @PostMapping("/user")
    public ResponseEntity<UserDetailsResponse> saveUser(@RequestBody AuthenticationRequest authenticationRequest);
}
