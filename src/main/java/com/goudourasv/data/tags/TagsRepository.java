package com.goudourasv.data.tags;

import com.goudourasv.domain.tags.Tag;
import com.goudourasv.http.tags.dto.TagCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TagsRepository {
    private final EntityManager entityManager;

    public TagsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Tag> getTags() {
        String sqlQuery = "SELECT * FROM tags";
        @SuppressWarnings("unchecked")
        List<TagEntity> tagEntities = entityManager.createNativeQuery(sqlQuery, TagEntity.class).getResultList();

        List<Tag> tags = new ArrayList<>();
        for (TagEntity tagEntity : tagEntities) {
            Tag tag = new Tag(tagEntity.getName());
            tags.add(tag);
        }
        return tags;
    }

    public Tag getSpecificTag(String name) {
        TagEntity tagEntity = entityManager.find(TagEntity.class, name);

        if (tagEntity == null) {
            return null;
        }
        Tag tag = new Tag(tagEntity.getName());
        return tag;
    }

    public Tag createTag(TagCreate tagCreate) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagCreate.getName());

        entityManager.persist(tagEntity);
        entityManager.flush();

        Tag tag = new Tag(tagEntity.getName());
        return tag;

    }

    public boolean deleteTag(String name) {
        String sqlQuery = "DELETE FROM tags WHERE name = :name ";
        int deletedEntities = entityManager.createNativeQuery(sqlQuery,TagEntity.class).setParameter("name",name).executeUpdate();
                if(deletedEntities == 0){
                    return false;
                }
                return true;

    }

}
