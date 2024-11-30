package org.blogsphere.blog.Payload.Mapper;

import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Payload.Response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    
    public User toUser(RegisterRequest registerRequest) {
        return User.builder()
            .username(registerRequest.username())
            .email(registerRequest.email())
            .password(registerRequest.password())
            .firstName(registerRequest.firstName())
            .lastName(registerRequest.lastName())
            .roles(registerRequest.roles())
            .profilePicture(registerRequest.profilePicture())
            .bio(registerRequest.bio())
            .build();
    }

    public UserResponse toUserResponse(User user){
        return new UserResponse(
            user.getId().toString(),
            user.getUsername(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRoles(),
            user.getProfilePicture(),
            user.getBio(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
