package com.goudourasv.data.tags

import com.goudourasv.domain.tags.Tag
import com.goudourasv.http.tags.dto.TagCreate
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import com.goudourasv.data.tags.toTags


@ApplicationScoped
class TagsRepository(private val entityManager: EntityManager) {

    fun getTags(): MutableSet<Tag> {
        val sqlQuery = "SELECT * FROM tags"

        @Suppress("UNCHECKED_CAST")
        val tagEntities: MutableSet<TagEntity> =
            entityManager.createNativeQuery(sqlQuery, TagEntity::class.java).resultList.toMutableSet() as MutableSet<TagEntity>
        return tagEntities.toTags()
    }

    fun createTag(tagCreate: TagCreate): Tag {
        val tagEntity = TagEntity(name = tagCreate.name)

        entityManager.persist(tagEntity)
        entityManager.flush()
        return tagEntity.toTag()
    }

    fun deleteTag(name: String): Boolean {
        val sqlQuery = "DELETE FROM tags WHERE tags.name = :name"
        val deletedEntities: Int =
            entityManager.createNativeQuery(sqlQuery, TagEntity::class.java).setParameter("name", name).executeUpdate()
        if (deletedEntities == 0) {
            return false
        }
        return true
    }
}