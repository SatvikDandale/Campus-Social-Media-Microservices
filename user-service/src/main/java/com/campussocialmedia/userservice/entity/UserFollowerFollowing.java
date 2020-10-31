package com.campussocialmedia.userservice.entity;

import java.util.List;

public class UserFollowerFollowing {
	private String userName;
	private List<String> followers;
	private List<String> following;

	public UserFollowerFollowing(String userName, List<String> followers, List<String> following) {
		super();
		this.userName = userName;
		this.followers = followers;
		this.following = following;
	}

	public UserFollowerFollowing() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "UserFollowerFollowing [userName=" + userName + ", followers=" + followers + ", following=" + following
				+ "]";
	}

}
