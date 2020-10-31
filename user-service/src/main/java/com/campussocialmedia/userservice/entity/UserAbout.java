package com.campussocialmedia.userservice.entity;

import java.util.HashMap;
import java.util.List;

public class UserAbout {
	// private String userId;
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private List<Experience> experiences;
	private List<Fishpond> fishponds;
	private College collegeDetails;
	private PersonalDetails personalDetails;
	private String bio;
	private String intro;
	private HashMap<String, String> socialLinks;

	public UserAbout() {
		super();
	}

	public UserAbout(String userName) {
		super();
		this.userName = userName;
	}

	public UserAbout(String userName, String email, String password, String firstName, String lastName,
			College collegeDetails) {
		super();
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.collegeDetails = collegeDetails;
	}

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

	public UserAbout(String userName, String email, String firstName, String lastName, String phone,
			List<Long> followers, List<Long> following, List<Experience> experiences, List<Long> posts,
			List<Fishpond> fishponds, College collegeDetails, PersonalDetails personalDetails, String bio, String intro,
			HashMap<String, String> socialLinks) {
		super();
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.experiences = experiences;
		this.fishponds = fishponds;
		this.collegeDetails = collegeDetails;
		this.personalDetails = personalDetails;
		this.bio = bio;
		this.intro = intro;
		this.socialLinks = socialLinks;
	}

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

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Fishpond> getFishponds() {
		return fishponds;
	}

	public void setFishponds(List<Fishpond> fishponds) {
		this.fishponds = fishponds;
	}

	public HashMap<String, String> getSocialLinks() {
		return socialLinks;
	}

	public void setSocialLinks(HashMap<String, String> socialLinks) {
		this.socialLinks = socialLinks;
	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phone=" + phone + ", experiences=" + experiences + ", fishponds=" + fishponds
				+ ", collegeDetails=" + collegeDetails + ", personalDetails=" + personalDetails + ", bio=" + bio
				+ ", intro=" + intro + ", socialLinks=" + socialLinks + "]";
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

}
