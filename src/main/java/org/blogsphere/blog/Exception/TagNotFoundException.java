package org.blogsphere.blog.Exception;

import org.blogsphere.blog.Enum.EException;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(){
        super(EException.TAG_NOT_FOUND_EXCEPTION.getMessage());
    }
}
