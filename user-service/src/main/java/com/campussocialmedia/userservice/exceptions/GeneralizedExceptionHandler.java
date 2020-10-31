package com.campussocialmedia.userservice.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// import jdk.jshell.spi.ExecutionControl.UserException;

//Used to apply the logic to all controller classes
@ControllerAdvice
@RestController
public class GeneralizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllGeneralExceptions(Exception ex, WebRequest req) {

        // creating the exception response object
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                req.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<?> handleUsernameNotFoundException(UserNameNotFoundException ex, WebRequest req) {
        // create the exception response object

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "User with userName: " + ex.getMessage() + " not found", req.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameMismatch.class)
    public final ResponseEntity<?> userNameMismatch(UserNameMismatch ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "The token does not belong to the userName: " + ex.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

}