package org.blogsphere.blog.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotBlank
    @NotNull
    String username,

    @NotNull
    @NotBlank
    String password
) {
    
}
