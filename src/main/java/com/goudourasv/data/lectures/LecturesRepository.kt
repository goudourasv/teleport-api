package com.goudourasv.data.lectures

import com.goudourasv.data.courses.CourseEntity
import com.goudourasv.domain.lectures.Lecture
import com.goudourasv.domain.lectures.LectureData
import com.goudourasv.http.lectures.dto.LectureCreate
import com.goudourasv.http.lectures.dto.LectureUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.Query

@ApplicationScoped
class LecturesRepository(private val entityManager: EntityManager) {

    fun getFilteredLectures(courseId: UUID): List<Lecture> {
        var sqlQuery = "SELECT * FROM lectures"
        val parametersMap = mutableMapOf<String, Any>()
        sqlQuery += "WHERE course_id = :courseId"
        parametersMap["courseId"] = courseId

        val query: Query = entityManager.createNativeQuery(sqlQuery, LectureEntity::class.java)
        for (key in parametersMap.keys) {
            query.setParameter(key, parametersMap[key])
        }
        @Suppress("UNCHECKED_CAST")
        val lectureEntities: List<LectureEntity> = query.resultList as List<LectureEntity>
        return lectureEntities.toLectures()
    }

    fun getSpecificLecture(id: UUID): Lecture {
        val lectureEntity = entityManager.find(LectureEntity::class.java, id)
        return lectureEntity.toLecture()
    }

    fun createLecture(lectureCreate: LectureCreate): Lecture {
        val courseEntity = entityManager.getReference(CourseEntity::class.java, lectureCreate.courseId)
        val lectureEntity = LectureEntity(
            title = lectureCreate.title,
            startTime = lectureCreate.startTime,
            endTime = lectureCreate.endTime,
            uri = lectureCreate.uri,
            courseEntity = courseEntity
        )
        entityManager.persist(lectureEntity)
        entityManager.flush()

        return lectureEntity.toLecture()
    }

    fun deleteSpecificLecture(id: UUID): Boolean {
        val lectureEntity = entityManager.getReference(LectureEntity::class.java, id) ?: return false
        entityManager.remove(lectureEntity)
        return true
    }

    fun replaceLecture(id: UUID, lectureCreate: LectureCreate): Lecture {
        val courseEntity = entityManager.getReference(CourseEntity::class.java, lectureCreate.courseId)
        val lectureEntity = LectureEntity(
            id = id,
            title = lectureCreate.title,
            startTime = lectureCreate.startTime,
            endTime = lectureCreate.endTime,
            uri = lectureCreate.uri,
            courseEntity = courseEntity
        )

        entityManager.merge(lectureEntity)
        entityManager.flush()

        return lectureEntity.toLecture()
    }

    fun partiallyUpdateLecture(id: UUID, lectureUpdate: LectureUpdate): Lecture {
        val lectureEntity = entityManager.getReference(LectureEntity::class.java, id)

        if (lectureUpdate.title != null) {
            lectureEntity.title = lectureUpdate.title!!
        }

        if (lectureUpdate.startTime != null) {
            lectureEntity.startTime = lectureUpdate.startTime!!
        }

        if (lectureUpdate.endTime != null) {
            lectureEntity.endTime = lectureUpdate.endTime!!
        }

        if (lectureUpdate.uri != null) {
            lectureEntity.uri = lectureUpdate.uri!!
        }

        entityManager.merge(lectureEntity)
        entityManager.flush()

        return lectureEntity.toLecture()

    }
}
