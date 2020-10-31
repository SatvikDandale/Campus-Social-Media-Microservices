package com.campussocialmedia.authenticationservice.Exceptions;

import java.util.Date;

//This is the standard exception response template
//If getters and for this class are not written, you will get an exception
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ExceptionResponse() {
        super();
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetails() {
        return this.details;
    }

    @Override
    public String toString() {
        return "Timestamp: " + timestamp + "Message: " + message + ", details: " + details;
    }
}
