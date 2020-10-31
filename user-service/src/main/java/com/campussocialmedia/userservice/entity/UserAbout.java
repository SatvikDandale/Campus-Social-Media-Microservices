package com.campussocialmedia.userservice.entity;

public class UserAbout {
	private String userName;
	private String firstName;
	private String lastName;
	private College collegeDetails;
	private PersonalDetails personalDetails;
	private String bio;
	private String intro;

	public UserAbout(String userName, String firstName, String lastName, College collegeDetails,
			PersonalDetails personalDetails, String bio, String intro) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.collegeDetails = collegeDetails;
		this.personalDetails = personalDetails;
		this.bio = bio;
		this.intro = intro;
	}

	public UserAbout() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public College getCollegeDetails() {
		return collegeDetails;
	}

	public void setCollegeDetails(College collegeDetails) {
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
		return "UserAbout [bio=" + bio + ", collegeDetails=" + collegeDetails + ", firstName=" + firstName + ", intro="
				+ intro + ", lastName=" + lastName + ", personalDetails=" + personalDetails + ", userName=" + userName
				+ "]";
	}

}
