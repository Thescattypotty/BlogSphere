package org.blogsphere.blog.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.blogsphere.blog.Entity.Comment;
import org.blogsphere.blog.Entity.Post;
import org.blogsphere.blog.Entity.Tag;
import org.blogsphere.blog.EntityRepository.PostRepository;
import org.blogsphere.blog.EntityRepository.TagRepository;
import org.blogsphere.blog.Exception.PostNotFoundException;
import org.blogsphere.blog.Exception.TagNotFoundException;
import org.blogsphere.blog.IService.IPostService;
import org.blogsphere.blog.Payload.Mapper.CommentMapper;
import org.blogsphere.blog.Payload.Mapper.PostMapper;
import org.blogsphere.blog.Payload.Request.CommentRequest;
import org.blogsphere.blog.Payload.Request.PostRequest;
import org.blogsphere.blog.Payload.Response.PostResponse;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService{
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public void createPost(PostRequest postRequest) {
        Post post = postMapper.toPost(postRequest);
        post.setTags(new HashSet<>(tagRepository.findAllById(
            postRequest.tagsId().stream().map(UUID::fromString).collect(Collectors.toSet())
        )));
        postRepository.save(post);
    }
    
    @Override
    @Transactional
    public void updatePost(String id, PostRequest postRequest) {
        Post post = postRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new PostNotFoundException());
        
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
        post.setPublished(postRequest.isPublished());
        
        Set<Tag> updatedTags = getTagsByIds(postRequest.tagsId());
        // Regénérer un nouvel ensemble pour éviter la référence partagée
        post.setTags(new HashSet<>(updatedTags));
        
        postRepository.save(post);
    }
    
    @Override
    public PostResponse getPostById(String id) {
        return postRepository.findById(UUID.fromString(id))
            .map(postMapper::toPostResponse)
            .orElseThrow(() -> new PostNotFoundException());
    }
    
    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
            .stream()
            .map(postMapper::toPostResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<PostResponse> getPostsByTag(String tagId) {
        return postRepository.findByTagsId(UUID.fromString(tagId))
            .stream()
            .map(postMapper::toPostResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePost(String id) {
        if(postRepository.existsById(UUID.fromString(id)))
            postRepository.deleteById(UUID.fromString(id));
        else
            throw new PostNotFoundException();
    }
    
    @Override
    @Transactional
    public void writeComment(String id, CommentRequest commentRequest) {
        Post post = postRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new PostNotFoundException());
        Comment comment = commentMapper.toComment(commentRequest);

        //post.getComments().add(comment);

        postRepository.save(post);
    }

    @Override
    @Transactional
    public void addTag(String id, String tagId) {
        Post post = postRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new PostNotFoundException());
        Tag tag = tagRepository.findById(UUID.fromString(tagId))
            .orElseThrow(() -> new TagNotFoundException());
        post.getTags().add(tag);

        postRepository.save(post);
    }

    @Override
    @Transactional
    public void deleteTag(String id, String tagId) {
        Post post = postRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new PostNotFoundException());
        Tag tag = tagRepository.findById(UUID.fromString(tagId))
            .orElseThrow(() -> new TagNotFoundException());
            
        post.getTags().remove(tag);

        postRepository.save(post);
    }
 
    private Set<Tag> getTagsByIds(Set<String> tagsId){
        Set<Tag> tags = new HashSet<>();
        for(String tagId: tagsId){
            Optional<Tag> tagOptional = tagRepository.findById(UUID.fromString(tagId));
            tagOptional.ifPresent(tags::add);
        }
        return tags;
    }
}
