package com.campussocialmedia.userservice.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.campussocialmedia.userservice.entity.UserAbout;
import com.campussocialmedia.userservice.entity.UserDTO;
import com.campussocialmedia.userservice.entity.UserDetailsResponse;
import com.campussocialmedia.userservice.entity.UserFollowerFollowing;
import com.campussocialmedia.userservice.exceptions.ExceptionResponse;
import com.campussocialmedia.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResourceController {
    @Autowired
    private UserService service;

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
        UserDTO newUser = service.addUser(user);
        UserDetailsResponse response = new UserDetailsResponse(newUser.getUserName(), newUser.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/userDetails/{userName}")
    public ResponseEntity<?> findUserDetails(@PathVariable String userName) {
        UserDetailsResponse response = service.getUserDetails(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // to return self user object
    @GetMapping("/self")
    public ResponseEntity<?> self(@RequestHeader("Authorization") String token) {
        UserDTO user = service.getUserFromToken(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/userAbout/{userName}")
    public ResponseEntity<?> findUser(@PathVariable String userName) {
        try {
            UserAbout user = service.getUserAboutByUserName(userName);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                    "User with username: " + userName + " not found", "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    // Updated Details of user from "about" section are taken from frontend and
    // updated in database
    @PostMapping("editUser")
    public ResponseEntity<?> editUserAboutDetails(@RequestBody UserAbout userObject) {
        try {
            UserAbout user = service.updateUserAboutDetails(userObject);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                    "User with username: " + userObject.getUserName() + " not found", "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/userFollowerFollowing/{userName}")
    public ResponseEntity<?> findUserFollowerFollowing(@PathVariable String userName) {
        try {
            UserFollowerFollowing user = service.getUserFollowerFollowingByUserName(userName);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                    "User with username: " + userName + " not found", "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/userConvos/{userName}")
    public ResponseEntity<?> getAllConvoNames(@PathVariable String userName) {
        try {
            HashMap<String, List<String>> ConvosName = service.getAllConvoNames(userName);
            return new ResponseEntity<>(ConvosName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Conversations not found for the user", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/follow")
    public ResponseEntity<?> addFollowerFollowing(@RequestBody Map<String, String> jsonObject,
            @RequestHeader(name = "Authorization") String token) {
        // String jwt = token.substring(7);
        // String userName = jwtUtil.extractUsername(jwt);
        // System.out.print(userName);
        // String userName = jwtUtil.extractUsername(jwt);
        // System.out.print(userName);
        service.addFollowerFollowing(jsonObject.get("follower"), jsonObject.get("following"), token);
        return new ResponseEntity<>("Follower Added", HttpStatus.OK);

    }

}