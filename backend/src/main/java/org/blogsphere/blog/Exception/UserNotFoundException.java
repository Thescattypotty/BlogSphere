package org.blogsphere.blog.Exception;

import org.blogsphere.blog.Enum.EException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super(EException.USER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
