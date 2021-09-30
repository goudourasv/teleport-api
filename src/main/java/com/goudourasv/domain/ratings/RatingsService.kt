package com.goudourasv.domain.ratings

import com.goudourasv.data.ratings.RatingsRepository
import com.goudourasv.http.courses.dto.RatingCreate
import java.util.*
import javax.enterprise.context.ApplicationScoped

import javax.transaction.Transactional

@ApplicationScoped
class RatingsService(private val ratingsRepository: RatingsRepository) {
    @Transactional
    fun createRating(courseId: UUID,userId: UUID, ratingCreate: RatingCreate): Rating {
        return ratingsRepository.createRating(courseId,userId,ratingCreate)
    }


}