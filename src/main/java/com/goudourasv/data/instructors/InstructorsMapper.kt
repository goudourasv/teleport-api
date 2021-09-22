package com.goudourasv.data.instructors

import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.domain.institutions.Institution
import com.goudourasv.domain.instructors.Instructor

object InstructorsMapper {
    //TODO Refactor toInstructor to be an extension method on the class InstructorEntity

    fun toInstructor(
        instructorEntity: InstructorEntity,
        institutionEntities: List<InstitutionEntity>
    ): Instructor {
        //TODO Implement that in functional way
        val institutions = mutableListOf<Institution>()
        for (institutionEntity in institutionEntities) {
            val institution = Institution(
                institutionEntity.id,
                institutionEntity.name,
                institutionEntity.country,
                institutionEntity.city
            )
            institutions.add(institution)
        }
        return Instructor(
            instructorEntity.id!!,
            instructorEntity.firstName ?: "",
            instructorEntity.lastName ?: "",
            institutions
        )
    }


    fun toInstructors(instructorEntities: List<InstructorEntity>): List<Instructor> {
        //TODO Implement that in functional way
        val instructors = mutableListOf<Instructor>()
        for (instructorEntity in instructorEntities) {
            val institutionEntities = instructorEntity.institutionEntities ?: emptyList()
            val instructor = toInstructor(instructorEntity, institutionEntities)
            instructors.add(instructor)
        }
        return instructors
    }
}