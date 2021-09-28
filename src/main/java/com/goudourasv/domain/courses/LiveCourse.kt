package com.goudourasv.domain.courses

import com.goudourasv.domain.institutions.InstitutionData
import com.goudourasv.domain.instructors.InstructorData
import com.goudourasv.domain.lectures.LectureData
import com.goudourasv.domain.tags.Tag

data class LiveCourse(
    val title: String,
    val instructor: InstructorData,
    val institution: InstitutionData,
    val tags: Set<Tag>,
    val liveLecture: LectureData,
)