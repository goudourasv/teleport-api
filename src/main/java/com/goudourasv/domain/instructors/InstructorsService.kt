package com.goudourasv.domain.instructors

import com.goudourasv.data.instructors.InstructorsRepository
import com.goudourasv.http.instructors.dto.InstructorCreate
import com.goudourasv.http.instructors.dto.InstructorUpdate
import java.util.*

class InstructorsService(private val instructorsRepository: InstructorsRepository) {

    fun getInstructors(institutionId: UUID?): List<Instructor> {
        return instructorsRepository.getInstructors(institutionId)
    }

    fun getSpecificInstructor(id: UUID): Instructor {
        return instructorsRepository.getSpecificInstructor(id)
    }

    fun createNewInstructor(instructorCreate: InstructorCreate): Instructor {
        return instructorsRepository.createNewInstructor(instructorCreate)
    }

    fun deleteSpecificInstructor(id: UUID): Boolean {
        val deleted = instructorsRepository.deleteSpecificInstructor(id)
        return deleted
    }

    fun replaceInstructor(id: UUID, instructorCreate: InstructorCreate): Instructor {
        return instructorsRepository.replaceInstructor(id, instructorCreate)
    }

    fun partiallyUpdateInstructor(id: UUID, instructorUpdate: InstructorUpdate): Instructor {
        return instructorsRepository.partiallyUpdateInstructor(instructorUpdate, id)

    }


}