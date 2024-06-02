package org.blogs.blogsphere.IServices;

import java.util.List;
import java.util.Optional;

import org.blogs.blogsphere.Payload.Filters.CommentFilter;
import org.blogs.blogsphere.Payload.Request.CommentRequest;
import org.blogs.blogsphere.Payload.Response.CommentResponse;

public interface ICommentService {
    CommentResponse writeComment(CommentRequest commentRequest);

    List<CommentResponse> getAllComments();

    List<CommentResponse> getComments(CommentFilter commentFilter);
    void deleteComment(Long commentId);
}
