package com.goudourasv.data.ratings

import com.goudourasv.data.courses.CourseEntity
import com.goudourasv.data.users.UserEntity
import com.goudourasv.domain.ratings.Rating
import com.goudourasv.http.courses.dto.RatingCreate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager

@ApplicationScoped
class RatingsRepository(private val entityManager: EntityManager) {

    fun createRating(courseId: UUID, userId: UUID, ratingCreate: RatingCreate): Rating {
        val courseEntity = entityManager.getReference(CourseEntity::class.java, courseId)
        val userEntity = entityManager.getReference(UserEntity::class.java, userId)
        val ratingEntity = RatingEntity(
            courseEntity = courseEntity,
            userEntity = userEntity,
            userRating = ratingCreate.userRating,
            message = ratingCreate.message
        )
        return ratingEntity.toRating()
    }
}