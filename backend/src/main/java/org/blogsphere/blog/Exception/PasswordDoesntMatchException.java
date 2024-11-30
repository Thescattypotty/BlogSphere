package org.blogsphere.blog.Exception;

import org.blogsphere.blog.Enum.EException;

public class PasswordDoesntMatchException extends RuntimeException{
    public PasswordDoesntMatchException(){
        super(EException.PASSWORD_DOES_NOT_MATCH.getMessage());
    }
}
