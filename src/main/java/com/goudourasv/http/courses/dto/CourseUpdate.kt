package com.goudourasv.http.courses.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.goudourasv.domain.lectures.Lecture
import com.goudourasv.domain.lectures.LectureData
import java.time.Instant
import java.util.*


data class CourseUpdate(
    val title: String?,
    val startDate: Instant?,
    val endDate: Instant?,
    @JsonProperty("institution")
    val institutionId: UUID?,
    @JsonProperty("instructor")
    val instructorId: UUID?,
    val tags: MutableSet<String>? = mutableSetOf(),
    @JsonProperty("lectures")
    val lectures: List<LectureData>? = mutableListOf(),

)