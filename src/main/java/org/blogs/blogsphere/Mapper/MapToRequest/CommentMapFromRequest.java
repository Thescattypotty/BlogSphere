package org.blogs.blogsphere.Mapper.MapToRequest;

import org.blogs.blogsphere.Entities.Comment;
import org.blogs.blogsphere.Enum.EStatus;
import org.blogs.blogsphere.Exceptions.ResourceNotFoundException;
import org.blogs.blogsphere.Payload.Request.CommentRequest;
import org.blogs.blogsphere.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentMapFromRequest {

    @Autowired
    private static PostRepository postRepository;

    public static Comment mapToComment(CommentRequest commentRequest)
    {
        return new Comment(
            commentRequest.getContent(),
            EStatus.valueOf(commentRequest.getStatus()),
            postRepository.findById(commentRequest.getPost_id())
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find Post"))
        );
    }
}
