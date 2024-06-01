package org.blogs.blogsphere.IServices;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.blogs.blogsphere.Payload.Request.PostRequest;
import org.blogs.blogsphere.Payload.Response.PostResponse;

public interface IPostService {
    
    PostResponse writePost(PostRequest postRequest);
    List<PostResponse> getAllPosts();
    List<PostResponse> getPosts(Optional<String> status , Optional<String> username , Optional<Date> createdAt, Optional<String> category);
    void deletePost(Long postId);
    PostResponse updatePost(Long postId , PostRequest postRequest);
}
