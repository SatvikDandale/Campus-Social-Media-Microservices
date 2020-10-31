package com.campussocialmedia.userservice.exceptions;

public class UserNameMismatch extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public UserNameMismatch(String s) {
        super(s);
        this.message = s;
    }

    public UserNameMismatch(String msg, Throwable t) {
        super(msg, t);
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}