package com.goudourasv.data.tags

import com.goudourasv.domain.tags.Tag

fun TagEntity.toTag(): Tag {
    return Tag(
        name = this.name
    )
}

fun MutableSet<TagEntity>.toTags(): MutableSet<Tag> {
    return this.map { tagEntity -> tagEntity.toTag() }.toMutableSet()

}

fun Tag.toTagEntity(): TagEntity {
    return TagEntity(
        name = this.name
    )
}

fun MutableSet<String>?.stringsToTagEntities(): MutableSet<TagEntity> {
    return this?.map { tagName -> TagEntity(tagName) }?.toMutableSet() ?: mutableSetOf()
}
fun MutableSet<Tag>.tagsToTagEntities() : MutableSet<TagEntity>{
    return this.map { tag -> TagEntity(tag.toString()) }.toMutableSet()

}
