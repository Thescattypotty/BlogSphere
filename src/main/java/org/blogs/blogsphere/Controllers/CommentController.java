package org.blogs.blogsphere.Controllers;

import org.blogs.blogsphere.Payload.Filters.CommentFilter;
import org.blogs.blogsphere.Payload.Request.CommentRequest;
import org.blogs.blogsphere.Payload.Response.CommentResponse;
import org.blogs.blogsphere.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    

    @Autowired
    private CommentService commentService;


    @PostMapping
    public ResponseEntity<CommentResponse> writeComment(@RequestBody CommentRequest commentRequest)
    {
        return ResponseEntity.ok(commentService.writeComment(commentRequest));   
    }
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments()
    {
        return ResponseEntity.ok(commentService.getAllComments());
    }
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@RequestBody CommentFilter commentFilter)
    {
        return ResponseEntity.ok(commentService.getComments(commentFilter));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId)
    {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

}
