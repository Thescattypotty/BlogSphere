package org.blogsphere.blog.ExceptionHandler;

import org.blogsphere.blog.Exception.InvalidCredentialsException;
import org.blogsphere.blog.Exception.PasswordDoesntMatchException;
import org.blogsphere.blog.Exception.UserNotFoundException;
import org.blogsphere.blog.Payload.Response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleException(UserNotFoundException e){
        return new ErrorResponse();
    }
    
    @ExceptionHandler(PasswordDoesntMatchException.class)
    public ErrorResponse handleException(PasswordDoesntMatchException e){
        return new ErrorResponse();
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ErrorResponse handleException(InvalidCredentialsException e) {
        return new ErrorResponse();
    }
}
