package org.blogsphere.blog.Controller;

import java.util.List;
import java.util.Set;

import org.blogsphere.blog.Payload.Request.TagRequest;
import org.blogsphere.blog.Payload.Response.TagResponse;
import org.blogsphere.blog.Service.TagService;
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
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<Void> createTag(@RequestBody @Valid TagRequest tagRequest){
        tagService.createTag(tagRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTag(@PathVariable("id") String id , @RequestBody @Valid TagRequest tagRequest){
        tagService.updateTag(id, tagRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") String id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTag(@PathVariable("id") String id){
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags(){
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @PostMapping("/names")
    public ResponseEntity<List<String>> getTagsNameByIds(@RequestBody Set<String> tagsId){
        return ResponseEntity.ok(tagService.getTagsNameByIds(tagsId));
    }
}
