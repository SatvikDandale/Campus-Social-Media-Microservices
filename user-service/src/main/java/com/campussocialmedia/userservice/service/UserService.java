package com.campussocialmedia.userservice.service;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.campussocialmedia.userservice.Proxies.AuthServiceProxy;
import com.campussocialmedia.userservice.Proxies.MediaServiceProxy;
import com.campussocialmedia.userservice.entity.User;
import com.campussocialmedia.userservice.entity.UserAbout;
import com.campussocialmedia.userservice.entity.UserDTO;
import com.campussocialmedia.userservice.entity.UserDetailsResponse;
import com.campussocialmedia.userservice.entity.UserFollowerFollowing;
import com.campussocialmedia.userservice.repository.UserRepository;
import com.campussocialmedia.userservice.exceptions.UserNameMismatch;
import com.campussocialmedia.userservice.exceptions.UserNameNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthServiceProxy authServiceProxy;
    @Autowired
    private MediaServiceProxy mediaService;

    private User convertToEntity(UserDTO user) {
        return modelMapper.map(user, User.class);
    }

    private User convertToEntity(UserAbout user) {
        return modelMapper.map(user, User.class);
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private UserAbout convertToAbout(User user) {
        return modelMapper.map(user, UserAbout.class);
    }

    private UserFollowerFollowing convertToFollowerFollowing(User user) {
        return modelMapper.map(user, UserFollowerFollowing.class);
    }

    public UserDTO addUser(UserDTO user) {
        List<String> emptyList = new ArrayList<String>();
        user.setPersonalChats(emptyList);
        user.setGroups(emptyList);
        user.setFollowers(emptyList);
        user.setFollowing(emptyList);

        User userEntity = repository.addUser(convertToEntity(user));
        return convertToDTO(userEntity);
    }

    public UserDTO getUserByUserName(String userName) {
        User User = repository.findUserByUserName(userName);
        UserDTO userDTO = convertToDTO(User);
        return userDTO;
    }

    public UserDetailsResponse getUserDetails(String userName) {
        User user = repository.findUserByUserName(userName);
        if (user == null)
            throw new UserNameNotFoundException(userName);
        UserDetailsResponse response = new UserDetailsResponse(user.getUserName(), user.getPassword());
        return response;
    }

    public UserAbout getUserAboutByUserName(String userName) {
        User User = repository.findUserByUserName(userName);
        UserAbout userAbout = convertToAbout(User);
        return userAbout;
    }

    public void addFollowerFollowing(String follower, String following, String token) {

        Map<String, String> reqBody = new HashMap<String, String>();
        reqBody.put("token", token);
        ResponseEntity<Map<String, String>> response = authServiceProxy.decodeJwt(reqBody);
        String userName = response.getBody().get("userName");
        if (!userName.equals(follower))
            throw new UserNameMismatch(follower);

        User followerEntity = repository.findUserByUserName(follower);
        if (followerEntity == null)
            throw new UserNameNotFoundException("User " + follower + " Not Found");
        User followingEntity = repository.findUserByUserName(following);
        if (followingEntity == null)
            throw new UserNameNotFoundException("User " + following + " Not Found");

        List<String> followingList = followingEntity.getFollowing();
        // If user already in following list, No need to add the user in the list
        if (followingList.contains(follower))
            return;
        followingList.add(follower);
        followingEntity.setFollowing(followingList);
        repository.updateUser(followingEntity);
        // if not present then only add else throw exception
        List<String> followersList = followerEntity.getFollowers();
        followersList.add(following);
        followerEntity.setFollowers(followersList);
        repository.updateUser(followerEntity);
        // Nothing to return

    }

    public UserAbout updateUserAboutDetails(UserAbout user) {
        User updatedUser = convertToEntity(user);
        updatedUser = repository.updateUserAboutDetails(updatedUser);
        UserAbout userAbout = convertToAbout(updatedUser);
        return userAbout;
    }

    public UserFollowerFollowing getUserFollowerFollowingByUserName(String userName) {
        User User = repository.findUserByUserName(userName);
        UserFollowerFollowing userAbout = convertToFollowerFollowing(User);
        return userAbout;
    }

    public HashMap<String, List<String>> getAllConvoNames(String userName) {
        UserDTO user = getUserByUserName(userName);
        HashMap<String, List<String>> convoNames = new HashMap<String, List<String>>();
        convoNames.put("Personal", user.getPersonalChats());
        convoNames.put("Group", user.getGroups());
        return convoNames;
    }

    public void removeFollowerFollowing(String follower, String following, String token) {

        Map<String, String> reqBody = new HashMap<String, String>();
        reqBody.put("token", token);
        ResponseEntity<Map<String, String>> response = authServiceProxy.decodeJwt(reqBody);
        String jwtUserName = response.getBody().get("userName");

        if (!jwtUserName.equals(follower))
            throw new UserNameMismatch("Token does not match with userName");
        User followerEntity = repository.findUserByUserName(follower);
        if (followerEntity == null)
            throw new UserNameNotFoundException("User " + follower + " Not Found");
        User followingEntity = repository.findUserByUserName(following);
        if (followingEntity == null)
            throw new UserNameNotFoundException("User " + following + " Not Found");
        List<String> followersList = followingEntity.getFollowers();
        if (!followersList.contains(follower))
            return;
        followersList.remove(follower);
        followingEntity.setFollowers(followersList);
        repository.updateUser(followingEntity);

        List<String> followingList = followerEntity.getFollowing();
        if (!followingList.contains(following))
            return;
        followingList.remove(following);
        followerEntity.setFollowing(followingList);
        repository.updateUser(followerEntity);
    }

    public UserDTO getUserFromToken(String token) {
        Map<String, String> reqBody = new HashMap<String, String>();
        reqBody.put("token", token);
        ResponseEntity<Map<String, String>> response = authServiceProxy.decodeJwt(reqBody);
        String userName = response.getBody().get("userName");
        UserDTO user = getUserByUserName(userName);
        return user;
    }

    public String addProfilePhoto(String userName, MultipartFile file) {
        String fileURL = mediaService.uploadFile(file);
        User user = repository.findUserByUserName(userName);
        user.setProfilePhotoURL(fileURL);
        repository.updateUser(user);
        return fileURL;
    }

}
