package com.goudourasv.domain.courses

import com.goudourasv.domain.institutions.InstitutionData
import com.goudourasv.domain.instructors.InstructorData
import com.goudourasv.domain.lectures.LectureData
import com.goudourasv.domain.tags.Tag
import java.time.Instant
import java.util.*


data class Course(
    val id: UUID,
    val title: String,
    val institutionData: InstitutionData,
    val tags: MutableSet<Tag>,
    val startDate: Instant?,
    val endDate: Instant?,
    val lectureData: List<LectureData>,
    val instructorData: InstructorData,
    var courseRating: CourseRating? = null,
    var favourite: Boolean = false,
)

data class CourseRating(
    val rating: Double,
    val numOfRatings: Int,
)

