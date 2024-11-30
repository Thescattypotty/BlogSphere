package org.blogsphere.blog.Payload.Response;

import java.time.LocalDateTime;
import java.util.Set;

import org.blogsphere.blog.Enum.ERole;

import lombok.Builder;

@Builder
public record UserResponse(
    String id,

    String username,

    String email,

    String firstName,

    String lastName,

    Set<ERole> roles,

    String profilePicture,

    String bio,

    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
    
}
