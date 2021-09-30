package com.goudourasv.domain.lectures

import com.goudourasv.domain.courses.Course
import java.net.URI
import java.time.Instant
import java.util.*


//TODO Create URL property for lectures to link with (ex.ZOOM APP)
data class Lecture (
    val id: UUID,
    val title: String,
    val uri: URI,
    val course: Course,
    val startTime: Instant,
    val endTime: Instant,
    )
