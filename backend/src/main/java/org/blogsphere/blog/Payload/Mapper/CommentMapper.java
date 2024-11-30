package org.blogsphere.blog.Payload.Mapper;

import org.blogsphere.blog.Entity.Comment;
import org.blogsphere.blog.Payload.Request.CommentRequest;
import org.blogsphere.blog.Payload.Response.CommentResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {
    public Comment toComment(CommentRequest commentRequest){
        return null;
    }
    public CommentResponse toCommentResponse(Comment comment){
        return null;
    }
}
