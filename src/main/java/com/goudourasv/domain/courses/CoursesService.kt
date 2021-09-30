package com.goudourasv.domain.courses

import com.goudourasv.data.courses.CoursesRepository
import com.goudourasv.http.courses.dto.CourseCreate
import com.goudourasv.http.courses.dto.CourseUpdate
import com.goudourasv.http.users.dto.FavouriteCourseCreate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class CoursesService(private val coursesRepository: CoursesRepository) {
    @Transactional
    fun getFilteredCourses(institutionId: UUID?, tags: Set<String?>, instructorId: UUID?): List<Course?>? {
        return coursesRepository.getFilteredCourses(institutionId, tags, instructorId)
    }

    @Transactional
    fun getSpecificCourse(id: UUID): Course? {
        return coursesRepository.getSpecificCourse(id)
    }

    @Transactional
    fun deleteSpecificCourse(id: UUID): Boolean {
        val deleted = coursesRepository.deleteSpecificCourse(id)
        return deleted
    }

    @Transactional
    fun createCourse(courseCreate: CourseCreate): Course {
        return coursesRepository.createCourse(courseCreate)
    }

    @Transactional
    fun replaceCourse(id: UUID, courseCreate: CourseCreate): Course {
        return coursesRepository.replaceCourse(courseCreate, id)
    }



    @Transactional
    fun partiallyUpdateCourse(id: UUID, courseUpdate: CourseUpdate): Course {
        return coursesRepository.partiallyUpdateCourse(courseUpdate, id)

    }

    @Transactional
    fun getLiveCourses(): List<LiveCourse> {
        return coursesRepository.getLiveCourses()
    }

    @Transactional
    fun createFavourite(favouriteCourseCreate: FavouriteCourseCreate): Course {
        return coursesRepository.createFavouriteCourse(favouriteCourseCreate)
    }

    @Transactional
    fun deleteSpecificFavouriteCourse(userId: UUID, courseId: UUID): Boolean {
        val deleted = coursesRepository.deleteSpecificFavourite(userId, courseId)
        return deleted
    }

    @Transactional
    fun getFavouriteCourses(userId: UUID): List<Course> {
        return coursesRepository.getFavouriteCourses(userId)
    }
}