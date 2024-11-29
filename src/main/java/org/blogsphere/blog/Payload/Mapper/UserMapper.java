package org.blogsphere.blog.Payload.Mapper;

import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Payload.Request.UserRequest;
import org.blogsphere.blog.Payload.Response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    
    public User toUser(UserRequest userRequest){
        return null;
    }
    
    public User toUser(RegisterRequest registerRequest) {
        return null;
    }
    public UserResponse toUserResponse(User user){
        return null;
    }
}
