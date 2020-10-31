package com.campussocialmedia.userservice.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class PersonalDetails {
	private String hometown;
	private List<String> talents;
	private List<String> achievements;

	public PersonalDetails(String hometown, List<String> talents, List<String> achievements) {
		super();
		this.hometown = hometown;
		this.talents = talents;
		this.achievements = achievements;
	}

	public PersonalDetails() {
		super();
	}

	@Override
	public String toString() {
		return "PersonalDetails [hometown=" + hometown + ", talents=" + talents + ", achievements=" + achievements
				+ "]";
	}

	@DynamoDBAttribute
	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	@DynamoDBAttribute
	public List<String> getTalents() {
		return talents;
	}

	public void setTalents(List<String> talents) {
		this.talents = talents;
	}

	@DynamoDBAttribute
	public List<String> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<String> achievements) {
		this.achievements = achievements;
	}

}
