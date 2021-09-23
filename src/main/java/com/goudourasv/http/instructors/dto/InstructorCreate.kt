package com.goudourasv.http.instructors.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.NotBlank

data class InstructorCreate(
    @NotBlank val firstName: String,
    @NotBlank val lastName: String,
    @JsonProperty("institutions") val institutionsIds: List<UUID>
)
