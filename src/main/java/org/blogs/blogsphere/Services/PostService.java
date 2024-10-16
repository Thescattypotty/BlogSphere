package org.blogs.blogsphere.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.blogs.blogsphere.Entities.Post;
import org.blogs.blogsphere.Enum.EStatus;
import org.blogs.blogsphere.Exceptions.ResourceNotFoundException;
import org.blogs.blogsphere.IServices.IPostService;
import org.blogs.blogsphere.Mapper.MapToRequest.PostMapFromRequest;
import org.blogs.blogsphere.Mapper.MapToResponse.PostMapToResponse;
import org.blogs.blogsphere.Payload.Filters.PostFilter;
import org.blogs.blogsphere.Payload.Request.PostRequest;
import org.blogs.blogsphere.Payload.Response.PostResponse;
import org.blogs.blogsphere.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService implements IPostService
{
    @Autowired
    private PostRepository postRepository;

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find the post"));     
        postRepository.delete(post);   
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
            .map(PostMapToResponse::MapToPostResponse)
            .collect(Collectors.toList());            
    }

    @Override
    public List<PostResponse> getPosts(PostFilter postFilter) {
        return postRepository.findAll().stream()
            .filter(post -> postFilter.getStatus().map(s -> s.equals(post.getStatus().name())).orElse(true))
            .filter(post -> postFilter.getUsername().map(u -> u.equals(post.getCreatedBy().getUsername())).orElse(true))
            .filter(post -> postFilter.getCreatedAt().map(c -> c.equals(post.getCreatedAt())).orElse(true))
            .filter(post -> postFilter.getCategory().map(cat -> post.getCategories().stream().anyMatch(ca -> ca.getName().equalsIgnoreCase(cat))).orElse(true))
            .map(PostMapToResponse::MapToPostResponse)
            .collect(Collectors.toList());
    }

    @Override
    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find the post"));
        post.setContent(postRequest.getContent());
        post.setFeaturedImage(postRequest.getFeaturedImage());
        post.setTitle(postRequest.getTitle());
        post.setStatus(EStatus.valueOf(postRequest.getStatus()));
        return PostMapToResponse.MapToPostResponse(postRepository.save(post));
    }

    @Override
    public PostResponse writePost(PostRequest postRequest) {
        return PostMapToResponse.MapToPostResponse(postRepository.save(PostMapFromRequest.mapToPost(postRequest)));
    }

    public PostResponse getPostById(Long postId)
    {
        return PostMapToResponse.MapToPostResponse(
            postRepository.findById(postId)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Cannot find the post")
                )
            );
    }
    
}
