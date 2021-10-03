package com.goudourasv.http.instructors.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class InstructorUpdate(
    val firstName: String?,
    val lastName: String?,
    @JsonProperty("institutions") var institutionsIds: List<UUID?>
)
