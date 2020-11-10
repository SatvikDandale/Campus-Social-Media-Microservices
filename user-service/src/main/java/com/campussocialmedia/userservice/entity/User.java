package com.campussocialmedia.userservice.entity;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "UserTable")
public class User {

    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private List<Long> personalChats;
    private List<Long> groups;
    private List<String> followers;
    private List<String> following;
    private List<Experience> experiences;
    private List<Long> posts;
    private College collegeDetails;
    private PersonalDetails personalDetails;
    private String bio;
    private String intro;
    private HashMap<String, String> socialLinks;
    private String profilePhotoURL;

    public User() {
        super();
    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String email, String password, String firstName, String lastName, String phone,
            List<Long> personalChats, List<Long> groups, List<String> followers, List<String> following,
            List<Experience> experiences, List<Long> posts, College collegeDetails, PersonalDetails personalDetails,
            String bio, String intro, HashMap<String, String> socialLinks, String profilePhotoURL) {
        super();
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
        this.experiences = experiences;
        this.posts = posts;
        this.collegeDetails = collegeDetails;
        this.personalDetails = personalDetails;
        this.bio = bio;
        this.intro = intro;
        this.socialLinks = socialLinks;
        this.profilePhotoURL = profilePhotoURL;
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
    public List<Long> getPersonalChats() {
        return personalChats;
    }

    public void setPersonalChats(List<Long> personalChats) {
        this.personalChats = personalChats;
    }

    @DynamoDBAttribute
    public List<Long> getGroups() {
        return groups;
    }

    public void setGroups(List<Long> groups) {
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

    @DynamoDBAttribute
    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @DynamoDBAttribute
    public List<Long> getPosts() {
        return posts;
    }

    public void setPosts(List<Long> posts) {
        this.posts = posts;
    }

    @DynamoDBAttribute
    public College getCollegeDetails() {
        return collegeDetails;
    }

    public void setCollegeDetails(College collegeDetails) {
        this.collegeDetails = collegeDetails;
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

    @DynamoDBAttribute
    public HashMap<String, String> getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(HashMap<String, String> socialLinks) {
        this.socialLinks = socialLinks;
    }

    @DynamoDBAttribute
    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    @Override
    public String toString() {
        return "User [bio=" + bio + ", collegeDetails=" + collegeDetails + ", email=" + email + ", experiences="
                + experiences + ", firstName=" + firstName + ", followers=" + followers + ", following=" + following
                + ", groups=" + groups + ", intro=" + intro + ", lastName=" + lastName + ", password=" + password
                + ", personalChats=" + personalChats + ", personalDetails=" + personalDetails + ", phone=" + phone
                + ", posts=" + posts + ", profilePhotoURL=" + profilePhotoURL + ", socialLinks=" + socialLinks
                + ", userName=" + userName + "]";
    }

    /*
     * public User() { super(); }
     * 
     * public User(String userName, String password) { super(); this.userName =
     * userName; this.password = password; } public User(String userId, String
     * userName, String email, String password, String firstName, String lastName,
     * String phone, List<String> personalChats, List<String> groups, List<String>
     * followers, List<String> following, College collegeDetails) { this.userId =
     * userId; this.userName = userName; this.email = email; this.password =
     * password; this.firstName = firstName; this.lastName = lastName; this.phone =
     * phone; this.personalChats = personalChats; this.groups = groups;
     * this.followers = followers; this.following = following; this.collegeDetails =
     * collegeDetails; }
     * 
     * 
     * @DynamoDBAttribute public String getUserId() { return userId; }
     * 
     * public void setUserId(String userId) { this.userId = userId; }
     * 
     * @DynamoDBHashKey(attributeName = "userName") public String getUserName() {
     * return userName; }
     * 
     * public void setUserName(String userName) { this.userName = userName; }
     * 
     * @DynamoDBAttribute public String getEmail() { return email; }
     * 
     * public void setEmail(String email) { this.email = email; }
     * 
     * @DynamoDBAttribute public String getPassword() { return password; }
     * 
     * public void setPassword(String password) { this.password = password; }
     * 
     * @DynamoDBAttribute public String getFirstName() { return firstName; }
     * 
     * public void setFirstName(String firstName) { this.firstName = firstName; }
     * 
     * @DynamoDBAttribute public String getLastName() { return lastName; }
     * 
     * public void setLastName(String lastName) { this.lastName = lastName; }
     * 
     * @DynamoDBAttribute public String getPhone() { return phone; }
     * 
     * public void setPhone(String phone) { this.phone = phone; }
     * 
     * @DynamoDBAttribute public List<Long> getPersonalChats() { return
     * personalChats; }
     * 
     * public void setPersonalChats(List<Long> personalChats) { this.personalChats =
     * personalChats; }
     * 
     * @DynamoDBAttribute public List<Long> getGroups() { return groups; }
     * 
     * public void setGroups(List<Long> groups) { this.groups = groups; }
     * 
     * @DynamoDBAttribute public List<String> getFollowers() { return followers; }
     * 
     * public void setFollowers(List<String> followers) { this.followers =
     * followers; }
     * 
     * @DynamoDBAttribute public List<String> getFollowing() { return following; }
     * 
     * public void setFollowing(List<String> following) { this.following =
     * following; }
     * 
     * public User() { super(); }
     * 
     * public User(String userName, String password) { super(); this.userName =
     * userName; this.password = password; }
     * 
     * @DynamoDBAttribute public College getCollegeDetails() { return
     * collegeDetails; }
     * 
     * public void setCollegeDetails(College collegeDetails) { this.collegeDetails =
     * collegeDetails; }
     * 
     * @Override public String toString() { return "User [collegeDetails=" +
     * collegeDetails + ", email=" + email + ", firstName=" + firstName +
     * ", followers=" + followers + ", following=" + following + ", groups=" +
     * groups + ", lastName=" + lastName + ", password=" + password +
     * ", personalChats=" + personalChats + ", phone=" + phone + ", userId=" +
     * userId + ", userName=" + userName + "]"; }
     * 
     */

}
