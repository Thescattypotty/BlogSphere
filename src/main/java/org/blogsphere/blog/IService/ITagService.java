package org.blogsphere.blog.IService;

import java.util.List;

import org.blogsphere.blog.Payload.Request.TagRequest;
import org.blogsphere.blog.Payload.Response.TagResponse;

public interface ITagService {
    void createTag(TagRequest tagRequest);
    void updateTag(String id , TagRequest tagRequest);
    void deleteTag(String id);

    TagResponse getTagById(String id);

    List<TagResponse> getAllTags();
    
}
