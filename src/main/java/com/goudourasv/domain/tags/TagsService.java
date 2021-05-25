package com.goudourasv.domain.tags;

import com.goudourasv.http.tags.dto.TagCreate;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
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

    public Tag getSpecificTag(String name) {
        Tag specificTag = tagsMap.get(name);
        return specificTag;

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
