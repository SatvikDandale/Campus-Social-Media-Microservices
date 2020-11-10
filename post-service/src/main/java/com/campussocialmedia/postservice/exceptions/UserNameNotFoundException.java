package com.campussocialmedia.postservice.exceptions;

public class UserNameNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public UserNameNotFoundException(String s) {
        super(s);
        this.message = s;
    }

    public UserNameNotFoundException(String msg, Throwable t) {
        super(msg, t);
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }

}