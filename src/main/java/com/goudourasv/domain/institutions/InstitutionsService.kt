package com.goudourasv.domain.institutions

import com.goudourasv.data.institutions.InstitutionsRepository
import com.goudourasv.http.institutions.dto.InstitutionCreate
import com.goudourasv.http.institutions.dto.InstitutionUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class InstitutionsService(private val institutionsRepository: InstitutionsRepository) {

    @Transactional
    fun getFilteredInstitutions(country: String?, city: String?): List<Institution> {
        return institutionsRepository.getInstitutions(country, city)
    }

    @Transactional
    fun getSpecificInstitution(id: UUID): Institution? {
        return institutionsRepository.getSpecificInstitution(id)
    }

    @Transactional
    fun createInstitution(institutionCreate: InstitutionCreate): Institution {
        return institutionsRepository.createInstitution(institutionCreate)
    }

    @Transactional
    fun deleteSpecificInstitution(id: UUID): Boolean {
        val deleted = institutionsRepository.deleteSpecificInstitution(id)
        return deleted
    }

    @Transactional
    fun replaceInstitution(id: UUID, institutionCreate: InstitutionCreate): Institution {
        return institutionsRepository.replaceInstitution(institutionCreate, id)
    }

    @Transactional
    fun partiallyUpdateInstitution(id: UUID, institutionUpdate: InstitutionUpdate): Institution {
        return institutionsRepository.partiallyUpdateInstitution(institutionUpdate, id)
    }


}