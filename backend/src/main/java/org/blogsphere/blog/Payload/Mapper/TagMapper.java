package org.blogsphere.blog.Payload.Mapper;

import org.blogsphere.blog.Entity.Tag;
import org.blogsphere.blog.Payload.Request.TagRequest;
import org.blogsphere.blog.Payload.Response.TagResponse;
import org.springframework.stereotype.Service;

@Service
public class TagMapper {
    public Tag toTag(TagRequest tagRequest){
        return Tag.builder()
            .name(tagRequest.name())
            .description(tagRequest.description())
            .build();
    }
    public TagResponse toTagResponse(Tag tag){
        return new TagResponse(
            tag.getId().toString(),
            tag.getName(),
            tag.getDescription(),
            tag.getCreatedAt(),
            tag.getUpdatedAt(),
            tag.getCreatedBy()
        );
    }
}
