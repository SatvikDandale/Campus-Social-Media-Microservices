package com.campussocialmedia.authenticationservice.Security;

import java.util.ArrayList;

import com.campussocialmedia.authenticationservice.Entities.UserDetailsResponse;
import com.campussocialmedia.authenticationservice.Proxies.UserServiceProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
class UserDetailsService is used by spring security to get the details of user
We have to create our own version of UserDetailsService to fetch the user details from our database
The job of MyUserDetailsService is to return a User object containing username and password of the 
user whose username is passed or throw UsernameNotFoundException. 
*/

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserServiceProxy userServiceProxy;

	@Override
	public UserDetails loadUserByUsername(String inputUserName) throws UsernameNotFoundException {

		String userName;
		String password;

		try {
			ResponseEntity<UserDetailsResponse> response = userServiceProxy.findUserDetails(inputUserName);
			if (response.getStatusCode().value() != 200)
				throw new UsernameNotFoundException(inputUserName);
			UserDetailsResponse user = response.getBody();
			userName = user.getUserName();
			password = user.getPassword();
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("User not found", e);
		}

		// Keeping the user authorities blank
		return new User(userName, password, new ArrayList<>());
	}
}
