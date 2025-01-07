package org.blogsphere.blog.Payload.Response;

import java.time.LocalDateTime;
import java.util.Set;

public record PostResponse(
    String id,

    String title,

    String content,

    String coverImage,

    LocalDateTime createdAt,

    LocalDateTime updatedAt,

    LocalDateTime publishedAt,

    boolean isPublished,

    Set<String> tagsId,

    //Set<String> commentsId,

    String createdBy
) {
    
}
