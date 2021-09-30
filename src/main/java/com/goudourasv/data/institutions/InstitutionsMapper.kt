package com.goudourasv.data.institutions

import com.goudourasv.data.instructors.InstructorEntity
import com.goudourasv.domain.institutions.Institution
import com.goudourasv.domain.institutions.InstitutionData

fun InstitutionEntity.toInstitution(): Institution {
    return Institution(
        city = this.city,
        country = this.country,
        id = this.id!!,
        name = this.name
    )
}

fun List<InstitutionEntity>.toInstitutions(): List<Institution> {
    return this.map { institutionEntity -> institutionEntity.toInstitution() }
}

fun InstitutionData.toInstitutionEntity(): InstitutionEntity{
    return InstitutionEntity(
        id = this.id,
        name = this.name,
    )
}

fun InstitutionEntity.toInstitutionData(): InstitutionData {
    return InstitutionData(
        id = this.id!!,
        name = this.name,
    )
}
