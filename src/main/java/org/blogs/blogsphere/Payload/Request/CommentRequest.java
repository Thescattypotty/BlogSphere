package org.blogs.blogsphere.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequest {
    
    private String content;
    private String status;
    private Long post_id;
}
