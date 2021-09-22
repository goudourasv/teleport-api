package com.goudourasv.data.instructors

import com.goudourasv.data.courses.CourseEntity
import com.goudourasv.data.institutions.InstitutionEntity
import java.util.*
import javax.persistence.*

@Entity(name = "Instructors")
class InstructorEntity(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "institution_instructor",
        joinColumns = [JoinColumn(name = "instructor_id")],
        inverseJoinColumns = [JoinColumn(name = "institution_id")]
    )
    var institutionEntities: MutableList<InstitutionEntity>? = null,

    @OneToMany(mappedBy = "instructorEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    var courseEntities: MutableList<CourseEntity>? = null,
)