package com.revature.aspect;

import com.revature.model.exception.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Login Exception for valid user creation and login
 */
@ControllerAdvice
public class CatchLoginExceptionAdvice {

    /**
     * Exception for an invalid login/registration attempt
     * @param ice the invalid credentials login
     * @return the response entity that builds the exception using forbidden https status
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<?> handleInvalidCredentialsException(final InvalidCredentialsException ice){

        return new ResponseEntity<>(ice.getMessage(),HttpStatus.FORBIDDEN);
    }
}
