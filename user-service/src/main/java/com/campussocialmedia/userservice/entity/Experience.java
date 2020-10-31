package com.campussocialmedia.userservice.entity;

import java.security.Timestamp;

public class Experience {
	String title;
	String place;
	String organization;
	Timestamp from;
	Timestamp to;

	public Experience(String title, String place, String organization, Timestamp from, Timestamp to) {
		super();
		this.title = title;
		this.place = place;
		this.organization = organization;
		this.from = from;
		this.to = to;
	}

	public Experience() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Timestamp getFrom() {
		return from;
	}

	public void setFrom(Timestamp from) {
		this.from = from;
	}

	public Timestamp getTo() {
		return to;
	}

	public void setTo(Timestamp to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Experience [title=" + title + ", place=" + place + ", organization=" + organization + ", from=" + from
				+ ", to=" + to + "]";
	}

}
