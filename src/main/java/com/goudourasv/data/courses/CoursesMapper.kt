package com.goudourasv.data.courses

import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.data.institutions.toInstitutionData
import com.goudourasv.data.institutions.toInstitutionEntity
import com.goudourasv.data.instructors.InstructorEntity
import com.goudourasv.data.instructors.toInstructorData
import com.goudourasv.data.instructors.toInstructorEntity
import com.goudourasv.data.lectures.toLectureData
import com.goudourasv.data.lectures.toLectureEntity
import com.goudourasv.data.tags.toTagEntities
import com.goudourasv.data.tags.toTags
import com.goudourasv.domain.courses.Course
import com.goudourasv.domain.courses.CourseData
import com.goudourasv.domain.courses.LiveCourse
import com.goudourasv.domain.lectures.LectureData
import com.goudourasv.http.courses.dto.CourseCreate


fun CourseEntity.toCourse(): Course {

    return Course(
        id = this.id!!,
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        institutionData = institutionEntity.toInstitutionData(),
        instructorData = this.instructorEntity.toInstructorData(),
        lectureData = this.lectureEntities.map { it.toLectureData() },
        tags = this.tagEntities.toTags()
    )
}

fun List<CourseEntity>.toCourses(): List<Course> {
    return this.map { courseEntity -> courseEntity.toCourse() }
}

fun CourseCreate.toCourseEntity(
    institutionEntity: InstitutionEntity,
    instructorEntity: InstructorEntity
): CourseEntity {
    return CourseEntity(
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        institutionEntity = institutionEntity,
        instructorEntity = instructorEntity,
        tagEntities = this.tags.toTagEntities(),
        lectureEntities = this.lectures.map { lecture ->
            lecture.toLectureEntity()
        }.toMutableList(),
    )
}

fun Course.toCourseEntity(): CourseEntity {
    return CourseEntity(
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        institutionEntity = this.institutionData.toInstitutionEntity(),
        instructorEntity = this.instructorData.toInstructorEntity(),
        tagEntities = this.tags.toTagEntities(),
        lectureEntities = this.lectureData.map { lectureData ->
            lectureData.toLectureEntity()
        }.toMutableList(),
    )
}

fun List<Course>.toLiveCourses(): List<LiveCourse> {
    val lectureDataList = this.map { liveCourse -> liveCourse.lectureData[0] }
    val newLectureDataList = lectureDataList.map { lecture ->
        LectureData(
            lecture.id,
            lecture.title,
            lecture.uri,
            lecture.startTime,
            lecture.endTime
        )
    }
    var liveCourses = emptyList<LiveCourse>()
    for (lectureData in newLectureDataList) {
        liveCourses = this.map { course ->
            LiveCourse(
                course.title,
                course.instructorData,
                course.institutionData,
                course.tags,
                lectureData
            )
        }
    }
    return liveCourses
}

fun CourseEntity.toCourseData(): CourseData {
    return CourseData(
        id = this.id!!,
        title = this.title,
    )
}


