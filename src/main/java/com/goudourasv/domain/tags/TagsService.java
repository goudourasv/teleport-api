package com.goudourasv.domain.tags;

import com.goudourasv.data.tags.TagsRepository;
import com.goudourasv.http.tags.dto.TagCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TagsService {
    private TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @Transactional
    public List<Tag> getTagsList() {
        List<Tag> tags = tagsRepository.getTags();
        return tags;
    }

    @Transactional
    public Tag getSpecificTag(String name) {
        Tag specificTag = tagsRepository.getSpecificTag(name);
        return specificTag;

    }

    @Transactional
    public Tag createNewTag(TagCreate tagCreate) {
        Tag createdTag = tagsRepository.createTag(tagCreate);
        return createdTag;
    }

    @Transactional
    public boolean deleteSpecificTag(String name) {
        boolean deleted = tagsRepository.deleteTag(name);
        return true;

    }
}
