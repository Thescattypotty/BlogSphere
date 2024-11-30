package org.blogsphere.blog.Exception;

import org.blogsphere.blog.Enum.EException;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(){
        super(EException.POST_NOT_FOUND_EXCEPTION.getMessage());
    }
}
