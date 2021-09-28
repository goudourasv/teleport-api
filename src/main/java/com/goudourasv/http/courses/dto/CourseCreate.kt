package com.goudourasv.http.courses.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.goudourasv.domain.lectures.Lecture
import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank

data class CourseCreate(
    @NotBlank val title: String,
    val startDate: Instant,
    val endDate: Instant,
    @JsonProperty("institution")
    val institutionId: UUID,
    @JsonProperty("instructor")
    val instructorId: UUID,
    val tags: MutableList<String>? = mutableListOf(),
    @JsonProperty("lectures")
    val lectures: List<Lecture>?

)
