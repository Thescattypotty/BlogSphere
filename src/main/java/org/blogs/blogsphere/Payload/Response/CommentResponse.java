package org.blogs.blogsphere.Payload.Response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private String status;
    private String createrUsername;
    private Date created_at;
}
