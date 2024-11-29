package org.blogsphere.blog.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(

    @NotNull
    @NotBlank
    String oldPassword,

    @NotNull
    @NotBlank
    String newPassword
) {
    
}
