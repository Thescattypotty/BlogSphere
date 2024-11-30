package org.blogsphere.blog.Exception;

import org.blogsphere.blog.Enum.EException;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super(EException.BAD_CREDENTIALS_FOR_AUTHENTICATION.getMessage());
    }
}
