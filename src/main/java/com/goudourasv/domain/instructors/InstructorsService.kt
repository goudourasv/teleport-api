package com.goudourasv.domain.instructors

import com.goudourasv.data.instructors.InstructorsRepository
import com.goudourasv.http.instructors.dto.InstructorCreate
import com.goudourasv.http.instructors.dto.InstructorUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class InstructorsService(private val instructorsRepository: InstructorsRepository) {
    @Transactional
    fun getInstructors(institutionId: UUID?): List<Instructor> {
        return instructorsRepository.getInstructors(institutionId)
    }

    @Transactional
    fun getSpecificInstructor(id: UUID): Instructor? {
        return instructorsRepository.getSpecificInstructor(id)
    }

    @Transactional
    fun createNewInstructor(instructorCreate: InstructorCreate): Instructor {
        return instructorsRepository.createNewInstructor(instructorCreate)
    }

    @Transactional
    fun deleteSpecificInstructor(id: UUID): Boolean {
        val deleted = instructorsRepository.deleteSpecificInstructor(id)
        return deleted
    }

    @Transactional
    fun replaceInstructor(id: UUID, instructorCreate: InstructorCreate): Instructor {
        return instructorsRepository.replaceInstructor(id, instructorCreate)
    }

    @Transactional
    fun partiallyUpdateInstructor(id: UUID, instructorUpdate: InstructorUpdate): Instructor {
        return instructorsRepository.partiallyUpdateInstructor(instructorUpdate, id)

    }


}