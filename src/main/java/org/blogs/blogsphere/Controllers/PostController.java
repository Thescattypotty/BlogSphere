package org.blogs.blogsphere.Controllers;


import java.util.List;

import org.blogs.blogsphere.Payload.Filters.PostFilter;
import org.blogs.blogsphere.Payload.Request.PostRequest;
import org.blogs.blogsphere.Payload.Response.PostResponse;
import org.blogs.blogsphere.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin
@RestController
@RequestMapping(path = "/api/post")
public class PostController {
    
    @Autowired
    private PostService postService;


    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest)
    {
        return new ResponseEntity<>(postService.writePost(postRequest),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts()
    {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    @GetMapping
    public ResponseEntity<List<PostResponse>> getPostsByFilter(@RequestBody PostFilter postFilter)
    {
        return ResponseEntity.ok(postService.getPosts(postFilter));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("id") Long postId)
    {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable("id") Long postId,@RequestBody PostRequest postRequest)
    {
        return new ResponseEntity<>(postService.updatePost(postId, postRequest), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId)
    {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
    
}
