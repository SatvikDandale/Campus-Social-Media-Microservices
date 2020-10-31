package com.campussocialmedia.userservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.campussocialmedia.userservice.entity.User;
import com.campussocialmedia.userservice.entity.UserAbout;
import com.campussocialmedia.userservice.entity.UserDTO;
import com.campussocialmedia.userservice.entity.UserDetailsResponse;
import com.campussocialmedia.userservice.entity.UserFollowerFollowing;
import com.campussocialmedia.userservice.repository.UserRepository;
import com.campussocialmedia.userservice.exceptions.UserNameNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;

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

    public void addFollowerFollowing(String follower, String following) {

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

}
