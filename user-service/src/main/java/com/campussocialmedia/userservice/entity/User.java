package com.campussocialmedia.userservice.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "UserTable")
public class User {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private List<String> personalChats;
    private List<String> groups;
    private List<String> followers;
    private List<String> following;
    private College collegeDetails;
    private PersonalDetails personalDetails;
    private String bio;
    private String intro;

    @DynamoDBAttribute
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBHashKey(attributeName = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDBAttribute
    public List<String> getPersonalChats() {
        return personalChats;
    }

    public void setPersonalChats(List<String> personalChats) {
        this.personalChats = personalChats;
    }

    @DynamoDBAttribute
    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    @DynamoDBAttribute
    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    @DynamoDBAttribute
    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public User() {
        super();
    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    @DynamoDBAttribute
    public College getCollegeDetails() {
        return collegeDetails;
    }

    public void setCollegeDetails(College collegeDetails) {
        this.collegeDetails = collegeDetails;
    }

    public User(String userId, String userName, String email, String password, String firstName, String lastName,
            String phone, List<String> personalChats, List<String> groups, List<String> followers,
            List<String> following, College collegeDetails, PersonalDetails personalDetails, String bio, String intro) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.personalChats = personalChats;
        this.groups = groups;
        this.followers = followers;
        this.following = following;
        this.collegeDetails = collegeDetails;
        this.personalDetails = personalDetails;
        this.bio = bio;
        this.intro = intro;
    }

    @DynamoDBAttribute
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    @DynamoDBAttribute
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @DynamoDBAttribute
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "User [bio=" + bio + ", collegeDetails=" + collegeDetails + ", email=" + email + ", firstName="
                + firstName + ", followers=" + followers + ", following=" + following + ", groups=" + groups
                + ", intro=" + intro + ", lastName=" + lastName + ", password=" + password + ", personalChats="
                + personalChats + ", personalDetails=" + personalDetails + ", phone=" + phone + ", userId=" + userId
                + ", userName=" + userName + "]";
    }

}
