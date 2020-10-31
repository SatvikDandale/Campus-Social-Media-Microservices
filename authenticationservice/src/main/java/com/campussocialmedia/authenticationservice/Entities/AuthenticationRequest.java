package com.campussocialmedia.authenticationservice.Entities;

import java.io.Serializable;

/*
 * Defines input format for incoming Authentication requests
 */
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Object collegeDetails;
    private String phone;

    // need default constructor for JSON Parsing
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getCollegeDetails() {
        return collegeDetails;
    }

    public void setCollegeDetails(Object collegeDetails) {
        this.collegeDetails = collegeDetails;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest [collegeDetails=" + collegeDetails + ", email=" + email + ", firstName="
                + firstName + ", lastName=" + lastName + ", password=" + password + ", phone=" + phone + ", userName="
                + userName + "]";
    }

    public AuthenticationRequest(String userName, String password, String email, String firstName, String lastName,
            Object collegeDetails, String phone) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.collegeDetails = collegeDetails;
        this.phone = phone;
    }

}
