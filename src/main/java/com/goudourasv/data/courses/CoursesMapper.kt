package com.goudourasv.data.courses

import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.data.instructors.InstructorEntity
import com.goudourasv.data.lectures.LectureEntity
import com.goudourasv.data.tags.toTagEntities
import com.goudourasv.data.tags.toTags
import com.goudourasv.domain.courses.Course
import com.goudourasv.domain.courses.LiveCourse
import com.goudourasv.domain.institutions.InstitutionData
import com.goudourasv.domain.instructors.InstructorData
import com.goudourasv.domain.lectures.LectureData
import com.goudourasv.http.courses.dto.CourseCreate


fun CourseEntity.toCourse(): Course {

    return Course(
        id = this.id,
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        institutionData = InstitutionData(this.institutionEntity.id!!, this.institutionEntity.name),
        instructorData = InstructorData(
            this.instructorEntity.id!!,
            this.instructorEntity.firstName,
            this.instructorEntity.lastName
        ),
        lectureData = this.lectureEntities.map { lectureEntity ->
            LectureData(
                lectureEntity.id,
                lectureEntity.title,
                lectureEntity.uri,
                lectureEntity.startTime,
                lectureEntity.endTime
            )
        },
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
        lectureEntities = this.lectures?.map { lecture ->
            LectureEntity(
                lecture.id,
                lecture.title,
                lecture.uri,
                lecture.startTime,
                lecture.endTime
            )
        }?.toMutableList() ?: mutableListOf(),
        ratingEntities = mutableListOf(),
        id =
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


