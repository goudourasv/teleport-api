package com.goudourasv.data.lectures

import com.goudourasv.data.courses.CourseEntity
import java.net.URI
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity(name = "Lectures")
class LectureEntity(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Column
    var title: String,

    @Column(name = "URI")
    var uri: URI,

    @Column(name = "start_time")
    var startTime: Instant,

    @Column(name = "end_time")
    var endTime: Instant,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    var courseEntity: CourseEntity? = null,

)

