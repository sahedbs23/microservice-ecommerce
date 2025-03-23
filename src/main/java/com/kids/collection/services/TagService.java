package com.kids.collection.services;

import com.kids.collection.entity.Tag;
import com.kids.collection.repository.TagRepository;
import com.kids.collection.request.TagRequest;
import com.kids.collection.response.TagResponse;
import com.kids.collection.utils.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository repository;

    public Set<TagResponse> findTags(String tagName) {
        Page<Tag> tags = tagName == null
                ? repository.findAll(Pagination.fixed("name"))
                : repository.findByNameLikeIgnoreCase(tagName, Pagination.fixed("name"));

        return tags.stream()
                .map(TagService::toTagResponse)
                .collect(Collectors.toSet());
    }

    public TagResponse createTag(TagRequest request){
        Tag savedTag = repository.save(toTag(request));
        return  toTagResponse(savedTag);
    }

    public static TagResponse toTagResponse(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName());
    }

    private static Tag toTag(TagRequest request){
        Tag tempTag =  new  Tag();
        tempTag.setName(request.getName());
        tempTag.setActive(true);
        return tempTag;
    }
}
