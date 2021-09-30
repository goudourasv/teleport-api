package com.goudourasv.data.courses

import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.data.instructors.InstructorEntity
import com.goudourasv.data.lectures.LecturesRepository
import com.goudourasv.data.lectures.toLectureEntity
import com.goudourasv.data.tags.stringsToTagEntities
import com.goudourasv.data.users.UserEntity
import com.goudourasv.domain.courses.Course
import com.goudourasv.domain.courses.LiveCourse
import com.goudourasv.http.courses.dto.CourseCreate
import com.goudourasv.http.courses.dto.CourseUpdate
import com.goudourasv.http.users.dto.FavouriteCourseCreate
import java.time.Instant
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.Query
import javax.ws.rs.BadRequestException
import javax.ws.rs.NotFoundException


@ApplicationScoped
class CoursesRepository(private val entityManager: EntityManager, private val lecturesRepository: LecturesRepository) {

    fun getCourses(): List<Course> {
        var sqlQuery = "SELECT * FROM courses"

        @Suppress("UNCHECKED_CAST")
        val courseEntities =
            entityManager.createNativeQuery(sqlQuery, CourseEntity::class.java).resultList as List<CourseEntity>
        return courseEntities.toCourses()
    }

    fun getFilteredCourses(
        institutionId: UUID?,
        tags: Set<String?>,
        instructorId: UUID?
    ): List<Course?>? {
        var sqlQuery = "SELECT * FROM courses"
        if (tags.isNotEmpty()) {
            sqlQuery += " JOIN course_tag ON courses.id = course_tag.course_id"
        }
        if (institutionId != null || instructorId != null || tags.isNotEmpty()) {
            sqlQuery += " WHERE "
        }
        val parametersMap: MutableMap<String, Any> =
            HashMap()
        var isFirst = true
        if (institutionId != null) {
            if (isFirst) {
                sqlQuery += "institution_id = :institutionId"
                isFirst = false
            } else {
                sqlQuery += " AND institution_id = :institutionId"
            }
            parametersMap["institutionId"] = institutionId
        }
        if (instructorId != null) {
            if (isFirst) {
                sqlQuery += "instructor_id = :instructorId"
                isFirst = false
            } else {
                sqlQuery += " AND instructor_id = :instructorId"
            }
            parametersMap["instructorId"] = instructorId
        }
        if (tags.isNotEmpty()) {
            sqlQuery += if (isFirst) {
                "tag = :tag"
            } else {
                " AND tag = :tag"
            }
            parametersMap["tag"] = tags
        }
        val query: Query = entityManager.createNativeQuery(sqlQuery, CourseEntity::class.java)
        for (key in parametersMap.keys) {
            query.setParameter(key, parametersMap[key])
        }
        @Suppress("UNCHECKED_CAST")
        val courseEntities: List<CourseEntity> = query.resultList as MutableList<CourseEntity>
        return courseEntities.toCourses()
    }

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
            val newTagEntities = courseUpdate.tags.stringsToTagEntities()
            courseEntity.replaceTagEntities(newTagEntities)
        }

        if (courseUpdate.lectures != null) {
            val lecturesToUpdate = courseUpdate.lectures
            val lectureEntities = lecturesToUpdate.map {
                it.toLectureEntity()
            }.toMutableList()
            courseEntity.replaceLectureEntities(lectureEntities)
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

    fun createFavouriteCourse(favouriteCourseCreate: FavouriteCourseCreate): Course {
        val favouritedCourseEntity =
            entityManager.getReference(CourseEntity::class.java, favouriteCourseCreate.courseId)
        val user = entityManager.getReference(UserEntity::class.java, favouriteCourseCreate.userId)
        favouritedCourseEntity.addUserToFavouritesSet(user)
        entityManager.merge(favouritedCourseEntity)
        entityManager.flush()
        return favouritedCourseEntity.toCourse()
    }

    fun deleteSpecificFavourite(userId: UUID, courseId: UUID): Boolean {
        val courseEntity = entityManager.getReference(CourseEntity::class.java, courseId)
        val user = entityManager.getReference(UserEntity::class.java, userId)
        courseEntity.deleteUserFromFavouritesSet(user)

        entityManager.merge(courseEntity)
        entityManager.flush()

        return true
    }

    fun getFavouriteCourses(userId: UUID): List<Course> {
        val courseEntities = entityManager.createNamedQuery("list_favourite_courses", CourseEntity::class.java)
            .setParameter("user_id", userId).resultList

        return courseEntities.toCourses()

    }


}


