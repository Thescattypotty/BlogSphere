package org.blogsphere.blog.Payload.Response;

import java.time.LocalDateTime;

public record TagResponse(
    String id,

    String name,
    
    String description,

    LocalDateTime createdAt,

    LocalDateTime updatedAt,

    String createdBy
) {
    
}
