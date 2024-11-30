package org.blogsphere.blog.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.blogsphere.blog.Entity.Tag;
import org.blogsphere.blog.EntityRepository.TagRepository;
import org.blogsphere.blog.Exception.TagNotFoundException;
import org.blogsphere.blog.IService.ITagService;
import org.blogsphere.blog.Payload.Mapper.TagMapper;
import org.blogsphere.blog.Payload.Request.TagRequest;
import org.blogsphere.blog.Payload.Response.TagResponse;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService{
    
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public void createTag(TagRequest tagRequest) {
        tagRepository.save(tagMapper.toTag(tagRequest));
    }

    @Override
    public void updateTag(String id, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new TagNotFoundException());
        tag.setName(tagRequest.name());
        tag.setDescription(tagRequest.description());

        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(String id) {
        if(tagRepository.existsById(UUID.fromString(id)))
            tagRepository.deleteById(UUID.fromString(id));
        else
            throw new TagNotFoundException();
    }

    @Override
    public TagResponse getTagById(String id) {
        return tagRepository.findById(UUID.fromString(id))
            .map(tagMapper::toTagResponse)
            .orElseThrow(() -> new TagNotFoundException());
    }

    @Override
    public List<TagResponse> getAllTags() {
        return tagRepository.findAll()
            .stream()
            .map(tagMapper::toTagResponse)
            .collect(Collectors.toList());
    }
    
}
