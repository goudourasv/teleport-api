package com.goudourasv.tags.service;

import com.goudourasv.tags.controller.dto.TagCreate;
import com.goudourasv.tags.domain.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TagsService {
    HashMap<String, Tag> tagsMap = new HashMap<>();



    public TagsService() {
        Tag tsakra = new Tag("tsakra");
        Tag engineering = new Tag("engineering");
        Tag software = new Tag("software");
        Tag math = new Tag("math");
        tagsMap.put("tsakra", tsakra);
        tagsMap.put("engineering", engineering);
        tagsMap.put("software", software);
        tagsMap.put("math", math);
    }


    public List<Tag> getTagsList() {
        List<Tag> tags = new ArrayList<>(tagsMap.values());
        return tags;
    }

    public Tag createNewTag(TagCreate tag) {
        Tag createdTag = new Tag(tag.getName());
        tagsMap.put(tag.getName(), createdTag);
        return createdTag;
    }

    public void deleteSpecificTag(String name) {
        tagsMap.remove(name);

    }
}
