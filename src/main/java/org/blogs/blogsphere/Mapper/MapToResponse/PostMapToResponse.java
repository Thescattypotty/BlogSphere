package org.blogs.blogsphere.Mapper.MapToResponse;

import java.util.stream.Collectors;

import org.blogs.blogsphere.Entities.Post;
import org.blogs.blogsphere.Payload.Response.PostResponse;

public class PostMapToResponse {
    public static PostResponse MapToPostResponse(Post post)
    {
        return new PostResponse(
            post.getTitle(),
            post.getFeaturedImage(),
            post.getStatus().name(),
            post.getComments().stream().map(CommentMapToResponse::MapToCommentResponse).collect(Collectors.toList()),
            post.getCategories().stream().map(CategorieMapToResponse::MapToCategorieResponse).collect(Collectors.toList()),
            post.getCreatedBy().getUsername(),
            post.getCreatedAt()
        );
    }  
}
