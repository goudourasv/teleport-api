package com.goudourasv.data.instructors

import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.domain.instructors.Instructor
import com.goudourasv.http.instructors.dto.InstructorCreate
import com.goudourasv.http.instructors.dto.InstructorUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.Query

@ApplicationScoped
class InstructorsRepository(private val entityManager: EntityManager) {
    fun getInstructors(institutionId: UUID?): List<Instructor> {
        val instructorsQuery: Query = if (institutionId == null) {
            val sqlQuery = "SELECT * FROM instructors"
            entityManager.createNativeQuery(sqlQuery, InstructorEntity::class.java)
        } else {
            val sqlQuery =
                "SELECT * FROM instructors JOIN institution_instructor ON instructors.id = institution_instructor.instructor_id WHERE institution_id = :institutionId"
            entityManager.createNativeQuery(sqlQuery, InstructorEntity::class.java)
                .setParameter("institutionId", institutionId)
        }

        @Suppress("UNCHECKED_CAST")
        val instructorEntities: List<InstructorEntity> = instructorsQuery.resultList as List<InstructorEntity>
        return instructorEntities.toInstructors()
    }

    fun getSpecificInstructor(id: UUID?): Instructor {
        val instructorEntity = entityManager.find(InstructorEntity::class.java, id)
        val institutionEntities = instructorEntity.institutionEntities ?: emptyList()
        return instructorEntity.toInstructor(institutionEntities)
    }

    fun createNewInstructor(instructorCreate: InstructorCreate): Instructor {
        val instructorEntity =
            InstructorEntity(firstName = instructorCreate.firstName, lastName = instructorCreate.lastName)
        val institutionIds = instructorCreate.institutionsIds
        val institutionEntities =
            institutionIds.map { id -> entityManager.getReference(InstitutionEntity::class.java, id) }.toMutableList()
        instructorEntity.institutionEntities = institutionEntities
        entityManager.persist(instructorEntity)
        entityManager.flush()
        return instructorEntity.toInstructor(institutionEntities)
    }

    fun deleteSpecificInstructor(id: UUID?): Boolean {
        val instructorEntity = entityManager.getReference(InstructorEntity::class.java, id) ?: return false
        entityManager.remove(instructorEntity)
        return true
    }

    fun replaceInstructor(id: UUID?, instructorCreate: InstructorCreate): Instructor {
        val instructorEntity =
            entityManager.getReference(InstructorEntity::class.java, id)
        instructorEntity.firstName = instructorCreate.firstName
        instructorEntity.lastName = instructorCreate.lastName
        val institutionIds = instructorCreate.institutionsIds
        val institutionEntities =
            institutionIds.map { institutionId ->
                entityManager.getReference(
                    InstitutionEntity::class.java,
                    institutionId
                )
            }.toMutableList()
        instructorEntity.institutionEntities = institutionEntities
        entityManager.merge(instructorEntity)
        entityManager.flush()
        return instructorEntity.toInstructor(institutionEntities)
    }

    fun partiallyUpdateInstructor(instructorUpdate: InstructorUpdate, id: UUID?): Instructor {
        val instructorEntity =
            entityManager.getReference(InstructorEntity::class.java, id)
        if (instructorUpdate.firstName != null) {
            val newInstructorFirstName = instructorUpdate.firstName!!
            instructorEntity.firstName = newInstructorFirstName
        }
        if (instructorUpdate.lastName != null) {
            val newInstructorLastName = instructorUpdate.lastName!!
            instructorEntity.lastName = newInstructorLastName
        }
        if (instructorUpdate.institutionsIds != null) {
            val newInstitutionIds = instructorUpdate.institutionsIds
            val institutionEntities =
                newInstitutionIds.map { institutionId ->
                    entityManager.getReference(
                        InstitutionEntity::class.java,
                        institutionId
                    )
                }
                    .toMutableList()
            instructorEntity.institutionEntities = institutionEntities
        }
        entityManager.merge(instructorEntity)
        entityManager.flush()
        val institutionEntities = instructorEntity.institutionEntities ?: emptyList()
        return instructorEntity.toInstructor(institutionEntities)
    }
}