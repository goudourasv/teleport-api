package com.goudourasv.domain.lectures

import com.goudourasv.data.lectures.LecturesRepository
import com.goudourasv.http.lectures.dto.LectureCreate
import com.goudourasv.http.lectures.dto.LectureUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class LecturesService(private val lecturesRepository: LecturesRepository) {

    @Transactional
    fun getFilteredLectures(courseId: UUID): List<Lecture> {
        return lecturesRepository.getFilteredLectures(courseId)
    }

    @Transactional
    fun getSpecificLecture(lectureId: UUID): Lecture {
        return lecturesRepository.getSpecificLecture(lectureId)
    }

    @Transactional
    fun createLecture(lectureCreate: LectureCreate): Lecture {
        return lecturesRepository.createLecture(lectureCreate)
    }

    @Transactional
    fun deleteSpecificLecture(lectureId: UUID): Boolean {
        val deleted = lecturesRepository.deleteSpecificLecture(lectureId)
        return deleted
    }

    @Transactional
    fun replaceLecture(id: UUID, lectureCreate: LectureCreate): Lecture {
        return lecturesRepository.replaceLecture(id,lectureCreate)
    }

    @Transactional
    fun partiallyUpdateLecture(id: UUID, lectureUpdate: LectureUpdate): Lecture{
        return lecturesRepository.partiallyUpdateLecture(id,lectureUpdate)

    }
}