package com.goudourasv.data.institutions

import com.goudourasv.domain.institutions.Institution
import com.goudourasv.http.institutions.dto.InstitutionCreate
import com.goudourasv.http.institutions.dto.InstitutionUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.Query

@ApplicationScoped
class InstitutionsRepository(private val entityManager: EntityManager) {
    //TODO complete this function
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

    fun deleteSpecificInstitution(id: UUID): Boolean {
        val institutionEntity = entityManager.getReference(InstitutionEntity::class.java, id) ?: return false
        entityManager.remove(institutionEntity)
        return true
    }

    fun replaceInstitution(institutionCreate: InstitutionCreate, id: UUID): Institution {
        val institutionEntity = InstitutionEntity(
            id = id,
            name = institutionCreate.name,
            city = institutionCreate.city,
            country = institutionCreate.country
        )
        //TODO Am I to deal with NotFoundException here or in http layer?
        entityManager.merge(institutionEntity)

        return institutionEntity.toInstitution()
    }

    fun partiallyUpdateInstitution(institutionUpdate: InstitutionUpdate, id: UUID): Institution {
        val institutionEntity = entityManager.getReference(InstitutionEntity::class.java, id)
        if (institutionUpdate.name != null) {
            val newInstitutionName = institutionUpdate.name
            institutionEntity.name = newInstitutionName
        }
        if (institutionUpdate.city != null) {
            val newInstitutionCity = institutionUpdate.city
            institutionEntity.city = newInstitutionCity
        }
        if (institutionUpdate.country != null) {
            val newInstitutionCountry = institutionUpdate.country
            institutionEntity.country = newInstitutionCountry
        }

        entityManager.merge(institutionEntity)
        entityManager.flush()

        return institutionEntity.toInstitution()

    }


}