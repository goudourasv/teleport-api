package com.goudourasv.data.instructors

import com.goudourasv.data.courses.CourseEntity
import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.domain.institutions.Institution
import com.goudourasv.domain.instructors.Instructor
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

fun InstructorEntity.toInstructor(

    institutionEntities: List<InstitutionEntity>
): Instructor {
    //TODO Implement that in functional way
    val institutions =
        institutionEntities.map { institutionEntity ->
            Institution(
                institutionEntity.id,
                institutionEntity.name,
                institutionEntity.country,
                institutionEntity.city
            )
        }.toMutableList()
    return Instructor(
        this.id!!,
        this.firstName ?: "",
        this.lastName ?: "",
        institutions
    )
}

fun toInstructors(instructorEntities: List<InstructorEntity>): List<Instructor> {
    //TODO Implement that in functional way
    val instructors = instructorEntities.map { instructorEntity ->
        instructorEntity.toInstructor(instructorEntity.institutionEntities ?: emptyList<InstitutionEntity>())
    }
    return instructors
}


