package org.blogs.blogsphere.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.blogs.blogsphere.Entities.Comment;
import org.blogs.blogsphere.Enum.EStatus;
import org.blogs.blogsphere.Exceptions.ResourceNotFoundException;
import org.blogs.blogsphere.IServices.ICommentService;
import org.blogs.blogsphere.Mapper.MapToResponse.CommentMapToResponse;
import org.blogs.blogsphere.Payload.Request.CommentRequest;
import org.blogs.blogsphere.Payload.Response.CommentResponse;
import org.blogs.blogsphere.Repositories.CommentRepository;
import org.blogs.blogsphere.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentService implements ICommentService
{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Override
    public CommentResponse writeComment(CommentRequest commentRequest) {
        Comment comment = new Comment(
            commentRequest.getContent(),
            EStatus.valueOf(commentRequest.getStatus()),
            postRepository.findById(commentRequest.getPost_id())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find the post"))
        );
        return CommentMapToResponse.MapToCommentResponse(commentRepository.save(comment));
    }

    @Override
    public List<CommentResponse> getAllComments() {
        return commentRepository.findAll().stream()
                .map(CommentMapToResponse::MapToCommentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getComments(Optional<String> status, Optional<String> username,
            Optional<Long> postId) {
        return commentRepository.findAll().stream()
                .filter(comment -> status.map(s -> s.equals(comment.getStatus().name())).orElse(true))
                .filter(comment -> username.map(u -> u.equals(comment.getCreatedBy().getUsername())).orElse(true))
                .filter(comment -> postId.map(p -> p.equals(comment.getPost().getId())).orElse(true))
                .map(CommentMapToResponse::MapToCommentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find Comment"));
        commentRepository.delete(comment);
    }
}
