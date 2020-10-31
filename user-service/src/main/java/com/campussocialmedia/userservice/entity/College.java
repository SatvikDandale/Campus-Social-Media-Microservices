package com.campussocialmedia.userservice.entity;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class College implements Serializable {
	private static final long serialVersionUID = 4449726365885112352L;
	private String year;
	private String branch;
	private String collegeName;

	public College(String year, String branch, String collegeName) {
		super();
		this.year = year;
		this.branch = branch;
		this.collegeName = collegeName;
	}

	public College() {
		super();
	}

	@DynamoDBAttribute
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@DynamoDBAttribute
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@DynamoDBAttribute
	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public String toString() {
		return "College [year=" + year + ", branch=" + branch + ", collegeName=" + collegeName + "]";
	}

}
