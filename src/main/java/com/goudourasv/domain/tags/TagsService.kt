package com.goudourasv.domain.tags

import com.goudourasv.data.tags.TagsRepository
import com.goudourasv.http.tags.dto.TagCreate
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class TagsService(private val tagsRepository: TagsRepository) {
    @Transactional
    fun getTagsList(): MutableSet<Tag> {
        return tagsRepository.getTags()
    }


    @Transactional
    fun createNewTag(tagCreate: TagCreate): Tag {
        return tagsRepository.createTag(tagCreate)
    }

    @Transactional
    fun deleteSpecificTag(name: String?): Boolean {
        var deleted = tagsRepository.deleteTag(name)
        return deleted
    }
}