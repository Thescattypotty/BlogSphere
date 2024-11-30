package org.blogsphere.blog.IService;

import java.util.List;

import org.blogsphere.blog.Payload.Request.CommentRequest;
import org.blogsphere.blog.Payload.Request.PostRequest;
import org.blogsphere.blog.Payload.Response.PostResponse;

public interface IPostService {
    void createPost(PostRequest postRequest);
    void updatePost(String id , PostRequest postRequest);
    PostResponse getPostById(String id);
    List<PostResponse> getAllPosts();
    void deletePost(String id);

    void writeComment(String id , CommentRequest commentRequest);

    void addTag(String id , String tagId);
    void deleteTag(String id , String tagId);

}
