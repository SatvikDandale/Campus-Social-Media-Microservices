package com.campussocialmedia.userservice.entity;

public class Fishpond {

    private String text;
    private String timeStamp;

    public Fishpond() {
        super();
    }

    public Fishpond(String text, String timeStamp) {
        super();
        this.text = text;
        this.timeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Fishpond [text=" + text + ", timeStamp=" + timeStamp + "]";
    }

}
