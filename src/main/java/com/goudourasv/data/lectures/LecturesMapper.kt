package com.goudourasv.data.lectures

import com.goudourasv.data.courses.toCourse
import com.goudourasv.data.courses.toCourseData
import com.goudourasv.data.courses.toCourseEntity
import com.goudourasv.domain.lectures.Lecture
import com.goudourasv.domain.lectures.LectureData


fun LectureEntity.toLecture(): Lecture {
    return Lecture(
        id = this.id!!,
        title = this.title,
        uri = this.uri,
        startTime = this.startTime,
        endTime = this.endTime,
        course = this.courseEntity!!.toCourse(),
    )
}

fun List<LectureEntity>.toLectures(): List<Lecture> {
    return this.map { it.toLecture() }
}

fun List<Lecture>.toLectureEntities(): List<LectureEntity> {
    return this.map { it.toLectureEntity() }

}

fun Lecture.toLectureEntity(): LectureEntity{
    return LectureEntity(
        id = this.id,
        title = this.title,
        uri = this.uri,
        startTime = this.startTime,
        endTime = this.endTime,
        courseEntity = this.course.toCourseEntity(),
    )
}

fun LectureData.toLectureEntity(): LectureEntity {
    return LectureEntity(
        id = this.id,
        title = this.title,
        uri = this.uri,
        startTime = this.startTime,
        endTime = this.endTime,
        courseEntity = null,
    )
}

fun LectureEntity.toLectureData(): LectureData{
    return LectureData(
        id = this.id!!,
        title = this.title,
        uri = this.uri,
        startTime = this.startTime,
        endTime = this.endTime,
    )
}
fun Lecture.toLectureData() : LectureData {
    return  LectureData(
        id = this.id,
        title = this.title,
        uri = this.uri,
        startTime = this.startTime,
        endTime = this.endTime,
    )
}
