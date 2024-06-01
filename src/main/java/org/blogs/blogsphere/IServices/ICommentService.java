package org.blogs.blogsphere.IServices;

import java.util.List;
import java.util.Optional;

import org.blogs.blogsphere.Payload.Request.CommentRequest;
import org.blogs.blogsphere.Payload.Response.CommentResponse;

public interface ICommentService {
    CommentResponse writeComment(CommentRequest commentRequest);

    List<CommentResponse> getAllComments();

    List<CommentResponse> getComments(Optional<String> status, Optional<String> username, Optional<Long> postId);

    void deleteComment(Long commentId);
}
