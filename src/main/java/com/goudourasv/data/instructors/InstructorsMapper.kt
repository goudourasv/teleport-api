package com.goudourasv.data.instructors

import com.goudourasv.domain.institutions.Institution
import com.goudourasv.domain.instructors.Instructor

fun InstructorEntity.toInstructor(): Instructor {
    //TODO check with alex
    val institutions =
        institutionEntities?.map { institutionEntity ->
            Institution(

                country = institutionEntity.country,
                name = institutionEntity.name,
                id = institutionEntity.id!!,
                city = institutionEntity.city
            )
        } ?: emptyList()
    return Instructor(
        this.id!!,
        this.firstName,
        this.lastName,
        institutions
    )
}

fun List<InstructorEntity>.toInstructors(): List<Instructor> {
    val instructors = this.map { instructorEntity ->
        instructorEntity.toInstructor()
    }
    return instructors
}