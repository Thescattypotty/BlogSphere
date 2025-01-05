package org.blogsphere.blog.Controller;


import java.util.List;

import org.blogsphere.blog.Annotation.IsOwner;
import org.blogsphere.blog.Annotation.IsUser;
import org.blogsphere.blog.Payload.Request.CommentRequest;
import org.blogsphere.blog.Payload.Request.PostRequest;
import org.blogsphere.blog.Payload.Response.PostResponse;
import org.blogsphere.blog.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid PostRequest postRequest){
        postService.createPost(postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable("id") String id , @RequestBody @Valid PostRequest postRequest){
        postService.updatePost(id, postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") String id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<List<PostResponse>> getPostsByTag(@PathVariable("tagId") String tagId){
        return ResponseEntity.ok(postService.getPostsByTag(tagId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/comment/")
    public ResponseEntity<Void> createComment(@PathVariable("id") String id , @RequestBody @Valid CommentRequest commentRequest){
        postService.writeComment(id, commentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{postId}/{tagId}")
    public ResponseEntity<Void> addTagToPost(@PathVariable("postId") String postId , @PathVariable("tagId") String tagId){
        postService.addTag(postId, tagId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{tagId}")
    public ResponseEntity<Void> removeTagToPost(@PathVariable("postId") String postId,@PathVariable("tagId") String tagId) {
        postService.deleteTag(postId, tagId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
