package org.blogs.blogsphere.IServices;

import java.util.List;

import org.blogs.blogsphere.Payload.Filters.PostFilter;
import org.blogs.blogsphere.Payload.Request.PostRequest;
import org.blogs.blogsphere.Payload.Response.PostResponse;

public interface IPostService {
    
    PostResponse writePost(PostRequest postRequest);
    List<PostResponse> getAllPosts();
    List<PostResponse> getPosts(PostFilter postFilter);
    void deletePost(Long postId);
    PostResponse updatePost(Long postId , PostRequest postRequest);
    PostResponse getPostById(Long postId);
}
