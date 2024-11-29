package org.blogsphere.blog.Payload.Request;

public record UserRequest(

    String firstName,

    String lastName,

    String profilePicture,

    String bio
) {
    
}
