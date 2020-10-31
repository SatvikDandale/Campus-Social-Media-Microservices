package com.campussocialmedia.userservice.entity;

import java.util.List;

public class UserDTO {
	// private String userId;
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

	public UserDTO(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	// public String getUserId() {
	// return userId;
	// }
	//
	// public void setUserId(String userId) {
	// this.userId = userId;
	// }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getPersonalChats() {
		return personalChats;
	}

	public void setPersonalChats(List<String> personalChats) {
		this.personalChats = personalChats;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public List<String> getFollowing() {
		return following;
	}

	public void setFollowing(List<String> following) {
		this.following = following;
	}

	public UserDTO() {
	}

	public College getCollegeDetails() {
		return collegeDetails;
	}

	public void setCollegeDetails(College collegeDetails) {
		this.collegeDetails = collegeDetails;
	}

	public UserDTO(String userName, String email, String password, String firstName, String lastName,
			College collegeDetails) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.collegeDetails = collegeDetails;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "UserDTO [bio=" + bio + ", collegeDetails=" + collegeDetails + ", email=" + email + ", firstName="
				+ firstName + ", followers=" + followers + ", following=" + following + ", groups=" + groups
				+ ", intro=" + intro + ", lastName=" + lastName + ", password=" + password + ", personalChats="
				+ personalChats + ", personalDetails=" + personalDetails + ", phone=" + phone + ", userName=" + userName
				+ "]";
	}

}
