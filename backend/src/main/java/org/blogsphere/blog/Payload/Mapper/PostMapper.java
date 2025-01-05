package org.blogsphere.blog.Payload.Mapper;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.blogsphere.blog.Entity.Post;
import org.blogsphere.blog.Payload.Request.PostRequest;
import org.blogsphere.blog.Payload.Response.PostResponse;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    
    public Post toPost(PostRequest postRequest){
        return Post.builder()
            .title(postRequest.title())
            .content(postRequest.content())
            .coverImage(postRequest.coverImage())
            .isPublished(postRequest.isPublished())
            .publishedAt(postRequest.isPublished() == true ? LocalDateTime.now(): null) 
            .comments(new HashSet<>())
            .build();
    }
    public PostResponse toPostResponse(Post post){
        return new PostResponse(
            post.getId().toString(),
            post.getTitle(),
            post.getContent(),
            post.getCoverImage(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getPublishedAt(),
            post.isPublished(),
            post.getTags().stream().map(t -> t.getId().toString()).collect(Collectors.toSet()),
            post.getComments().stream().map(c -> c.getId().toString()).collect(Collectors.toSet()),
            post.getCreatedBy().getId().toString()
        );
    }
}
