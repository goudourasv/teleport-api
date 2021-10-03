package com.goudourasv.data.institutions

import com.goudourasv.domain.institutions.Institution
import com.goudourasv.http.institutions.dto.InstitutionCreate
import com.goudourasv.http.institutions.dto.InstitutionUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.Query
import javax.ws.rs.NotFoundException

@ApplicationScoped
class InstitutionsRepository(private val entityManager: EntityManager) {
    //TODO Refactor this ugly function
    fun getInstitutions(country: String?, city: String?): List<Institution> {
        var sqlQuery = " SELECT * FROM institutions"
        if (country != null || city != null) {
            sqlQuery += " WHERE "
        }

        val parametersMap = mutableMapOf<String, Any>()

        var isFirst = true
        if (country != null) {
            if (isFirst) {
                sqlQuery += "country = :country"
                isFirst = false
            } else {
                sqlQuery += " AND country = :country"
            }
            parametersMap["country"] = country
        }
        if (city != null) {
            sqlQuery += if (isFirst) {
                "city = :city"

            } else {
                " AND city = :city"
            }
            parametersMap["city"] = city
        }
        val query: Query = entityManager.createNativeQuery(sqlQuery, InstitutionEntity::class.java)
        for (key in parametersMap.keys) {
            query.setParameter(key, parametersMap[key])
        }
        @Suppress("UNCHECKED_CAST")
        val institutionEntities = query.resultList as List<InstitutionEntity>
        return institutionEntities.toInstitutions()
    }

    fun getSpecificInstitution(id: UUID): Institution? {
        val institutionEntity = entityManager.find(InstitutionEntity::class.java, id) ?: return null
        return institutionEntity.toInstitution()
    }

    fun createInstitution(institutionCreate: InstitutionCreate): Institution {
        val institutionEntity = InstitutionEntity(
            name = institutionCreate.name,
            city = institutionCreate.city,
            country = institutionCreate.country
        )
        entityManager.persist(institutionEntity)
        entityManager.flush()
        return institutionEntity.toInstitution()
    }

    //Handling the association before deleting element from non-owner
    fun deleteSpecificInstitution(id: UUID): Boolean {
        val institutionEntity = entityManager.find(InstitutionEntity::class.java, id) ?: return false
        institutionEntity.instructorEntities?.forEach { instructorEntity ->
            instructorEntity.institutionEntities?.remove(
                institutionEntity
            )
        }
        entityManager.remove(institutionEntity)
        return true
    }

    fun replaceInstitution(institutionCreate: InstitutionCreate, id: UUID): Institution {
        val institutionEntity = entityManager.find(InstitutionEntity::class.java, id)
            ?: throw NotFoundException("Institution with id: $id doesn't exist")
        institutionEntity.name = institutionCreate.name
        institutionEntity.city = institutionCreate.city
        institutionEntity.country = institutionCreate.country
        entityManager.merge(institutionEntity)
        entityManager.flush()


        return institutionEntity.toInstitution()
    }

    fun partiallyUpdateInstitution(institutionUpdate: InstitutionUpdate, id: UUID): Institution {
        val institutionEntity = entityManager.getReference(InstitutionEntity::class.java, id)

        if (institutionUpdate.name != null) {
            institutionEntity.name = institutionUpdate.name
        }
        if (institutionUpdate.city != null) {
            institutionEntity.city = institutionUpdate.city
        }
        if (institutionUpdate.country != null) {
            institutionEntity.country = institutionUpdate.country
        }

        entityManager.merge(institutionEntity)
        entityManager.flush()

        return institutionEntity.toInstitution()

    }


}