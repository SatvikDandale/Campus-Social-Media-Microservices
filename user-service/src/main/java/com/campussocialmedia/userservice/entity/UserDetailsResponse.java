package com.campussocialmedia.userservice.entity;

import java.io.Serializable;

/*
Specifies the Authentication response object.
Currently we need only one field in the Authentication Response object which is the JWT token.
*/
public class UserDetailsResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetailsResponse(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDetailsResponse [password=" + password + ", userName=" + userName + "]";
	}

}
