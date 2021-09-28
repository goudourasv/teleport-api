package com.goudourasv.data.courses

import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.data.instructors.InstructorEntity
import com.goudourasv.data.lectures.LectureEntity
import com.goudourasv.data.lectures.LecturesRepository
import com.goudourasv.data.tags.toTagEntities
import com.goudourasv.domain.courses.Course
import com.goudourasv.domain.courses.LiveCourse
import com.goudourasv.http.courses.dto.CourseCreate
import com.goudourasv.http.courses.dto.CourseUpdate
import java.time.Instant
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.ws.rs.BadRequestException
import javax.ws.rs.NotFoundException

@ApplicationScoped
class CoursesRepository(private val entityManager: EntityManager, private val lecturesRepository: LecturesRepository) {

    fun getCourses(): List<Course> {
        var sqlQuery = "SELECT * FROM courses"

        @SuppressWarnings("UNCHECKED_CAST")
        val courseEntities =
            entityManager.createNativeQuery(sqlQuery, CourseEntity::class.java).resultList as List<CourseEntity>
        return courseEntities.toCourses()
    }

//    fun getFilteredCourses(): List<Course> {
//
//    }

    fun getSpecificCourse(id: UUID): Course? {
        val courseEntity = entityManager.getReference(CourseEntity::class.java, id) ?: return null
        return courseEntity.toCourse()
    }

    private fun findInstitutionById(id: UUID): InstitutionEntity {
        return entityManager.getReference(InstitutionEntity::class.java, id)
            ?: throw BadRequestException("Institution with id  $id  doesn't exist")
    }

    private fun findInstructorById(id: UUID): InstructorEntity {
        return entityManager.getReference(InstructorEntity::class.java, id)
            ?: throw BadRequestException("Instructor with id  $id  doesn't exist")
    }


    fun createCourse(courseCreate: CourseCreate): Course {
        val institutionEntity = findInstitutionById(courseCreate.institutionId)
        val instructorEntity = findInstructorById(courseCreate.instructorId)
        val courseEntity = courseCreate.toCourseEntity(institutionEntity, instructorEntity)

        entityManager.persist(courseEntity)
        entityManager.flush()

        return courseEntity.toCourse()
    }


    fun deleteSpecificCourse(id: UUID): Boolean {
        val courseEntity = entityManager.find(CourseEntity::class.java, id) ?: return false

        entityManager.remove(courseEntity)
        return true
    }

    fun replaceCourse(courseCreate: CourseCreate, id: UUID): Course {
        val institutionEntity = findInstitutionById(courseCreate.institutionId)
        val instructorEntity = findInstructorById(courseCreate.instructorId)
        val courseEntity = courseCreate.toCourseEntity(institutionEntity, instructorEntity)
        courseEntity.id = id
        entityManager.merge(courseEntity) ?: throw NotFoundException("Course with id:  $id  doesn't exist")
        return courseEntity.toCourse()
    }

    fun partiallyUpdateCourse(courseUpdate: CourseUpdate, id: UUID): Course {
        val courseEntity = entityManager.find(CourseEntity::class.java, id)

        if (courseUpdate.title != null) {
            courseEntity.title = courseUpdate.title
        }

        if (courseUpdate.startDate != null) {
            courseEntity.startDate = courseUpdate.startDate
        }

        if (courseUpdate.endDate != null) {
            courseEntity.endDate = courseUpdate.endDate
        }

        if (courseUpdate.institutionId != null) {
            val newInstitutionId = courseUpdate.institutionId
            courseEntity.institutionEntity = entityManager.getReference(InstitutionEntity::class.java, newInstitutionId)
        }

        if (courseUpdate.instructorId != null) {
            val newInstructorId = courseUpdate.instructorId
            courseEntity.institutionEntity = entityManager.getReference(InstitutionEntity::class.java, newInstructorId)
        }

        if (courseUpdate.tags != null) {
            val newTags = courseUpdate.tags
            courseEntity.tagEntities = newTags.toTagEntities()
        }

        if (courseUpdate.lectures != null) {
            val lecturesToUpdate = courseUpdate.lectures
            courseEntity.lectureEntities = lecturesToUpdate.map { lecture ->
                LectureEntity(
                    lecture.id,
                    lecture.title,
                    lecture.uri,
                    lecture.startTime,
                    lecture.endTime
                )
            }.toMutableList()
        }

        entityManager.merge(courseEntity)
        entityManager.flush()

        return courseEntity.toCourse()
    }

    fun getLiveCourses(): List<LiveCourse> {
        var currentTimeStamp = Instant.now()
        val currentCourseEntities = entityManager.createNamedQuery("list_live_courses", CourseEntity::class.java)
            .setParameter("current_timestamp", currentTimeStamp).resultList
        val currentCourses = currentCourseEntities.toCourses()
        return currentCourses.toLiveCourses()

    }



}


