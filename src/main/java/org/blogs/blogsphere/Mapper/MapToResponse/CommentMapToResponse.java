package org.blogs.blogsphere.Mapper.MapToResponse;

import org.blogs.blogsphere.Entities.Comment;
import org.blogs.blogsphere.Payload.Response.CommentResponse;

public class CommentMapToResponse {
    

    public static CommentResponse MapToCommentResponse(Comment comment)
    {
        return new CommentResponse(
            comment.getId(),
            comment.getContent(),
            comment.getStatus().name(),
            comment.getCreatedBy().getUsername(),
            comment.getCreatedAt()
        );
    }
}
