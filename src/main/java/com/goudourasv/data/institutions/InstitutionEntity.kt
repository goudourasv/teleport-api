package com.goudourasv.data.institutions

import com.goudourasv.data.courses.CourseEntity
import com.goudourasv.data.instructors.InstructorEntity
import java.util.*
import javax.persistence.*

@Entity(name = "Institutions")
class InstitutionEntity(
    @Id
    @GeneratedValue
    var id: UUID? = null,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "city")
    var city: String? = null,
    @Column(name = "country")
    var country: String? = null,
    //TODO check why in kotlin CascadeType.ALL needs wrap with []
    @OneToMany(mappedBy = "institutionEntity", cascade = ([CascadeType.ALL]), orphanRemoval = true)
    var courseEntities: MutableList<CourseEntity>? = null,
    @ManyToMany(mappedBy = "institutionEntities")
    var instructorEntities: MutableSet<InstructorEntity>? = null,
)



