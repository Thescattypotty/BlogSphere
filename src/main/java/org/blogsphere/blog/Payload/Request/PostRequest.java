package org.blogsphere.blog.Payload.Request;

import java.util.Set;

public record PostRequest(
    String title,

    String content,

    boolean isPublished,

    Set<String> tagsId
) {
    
}
