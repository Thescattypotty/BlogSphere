package org.blogsphere.blog.Payload.Request;

import java.util.Set;

import org.blogsphere.blog.Enum.ERole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

    @NotBlank 
    @Size(min=3,max=20)
    String username,

    @NotBlank
    @Size(max = 50)
    @Email
    String email,

    @NotBlank
    @NotNull
    String password,

    String firstName,

    String lastName,

    @NotNull
    Set<ERole> roles,

    String profilePicture,

    String bio

) {
    
}
