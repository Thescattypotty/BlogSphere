package org.blogs.blogsphere.Mapper.MapToRequest;

import java.util.stream.Collectors;

import org.blogs.blogsphere.Entities.Post;
import org.blogs.blogsphere.Enum.EStatus;
import org.blogs.blogsphere.Payload.Request.PostRequest;
import org.blogs.blogsphere.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostMapFromRequest {

    @Autowired
    private static CategoryRepository categoryRepository;
    
    public static Post mapToPost(PostRequest postRequest)
    {        
        return new Post(
            postRequest.getTitle(),
            postRequest.getContent(),
            postRequest.getFeaturedImage(),
            EStatus.valueOf(postRequest.getStatus()),
                postRequest.getCategory_id().stream()
                        .map(categoryId -> categoryRepository.findById(categoryId).orElse(null))
                        .filter(category -> category != null)
                        .collect(Collectors.toSet())
        );
    }
}
