package org.blogsphere.blog.Payload.Response;

import java.time.LocalDateTime;

public record CommentResponse(
    String id,

    String content,

    String createdBy,

    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
    
}
