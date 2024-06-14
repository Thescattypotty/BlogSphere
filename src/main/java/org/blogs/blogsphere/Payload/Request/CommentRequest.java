package org.blogs.blogsphere.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequest {
    
    @NotBlank
    private String content;
    @NotBlank
    private String status;
    @NotBlank
    private Long post_id;
}
