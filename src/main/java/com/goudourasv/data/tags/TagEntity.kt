package com.goudourasv.data.tags

import com.goudourasv.data.courses.CourseEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity(name = "Tags")
class TagEntity(
    @Id
    var name: String,

    @ManyToMany(mappedBy = "tagEntities")
    var courseEntities: MutableSet<CourseEntity>? = mutableSetOf(),
)
