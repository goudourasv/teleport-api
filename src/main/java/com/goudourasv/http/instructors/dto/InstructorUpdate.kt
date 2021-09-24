package com.goudourasv.http.instructors.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class InstructorUpdate(
    var firstName: String?,
    var lastName: String?,
    @JsonProperty("institutions") var institutionsIds: List<UUID?>
)
