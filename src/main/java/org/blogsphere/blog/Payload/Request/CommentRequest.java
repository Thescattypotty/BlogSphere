package org.blogsphere.blog.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
    @NotNull
    @NotBlank
    String content
) {
    
}
