package com.goudourasv.data.institutions

import com.goudourasv.domain.institutions.Institution

fun InstitutionEntity.toInstitution(): Institution {
    return Institution(
        city = this.city,
        country = this.country,
        id = this.id!!,
        name = this.name
    )
}

fun List<InstitutionEntity>.toInstitutions(): List<Institution> {
    val institutions = this.map { institutionEntity -> institutionEntity.toInstitution() }
    return institutions

}
