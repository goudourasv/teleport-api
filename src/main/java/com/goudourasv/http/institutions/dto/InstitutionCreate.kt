package com.goudourasv.http.institutions.dto

import java.util.*
import javax.validation.constraints.NotBlank

data class InstitutionCreate(
    @NotBlank val name: String,
    @NotBlank val country: String,
    @NotBlank val city: String,
    val instructorIds: List<UUID> = emptyList()
)