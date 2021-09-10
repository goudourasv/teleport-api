package com.goudourasv.data.ratings;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.data.users.UserEntity;
import com.goudourasv.domain.ratings.Rating;
import com.goudourasv.http.courses.dto.RatingCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.UUID;

import static com.goudourasv.data.ratings.RatingsMapper.toRatingEntity;


@ApplicationScoped
public class RatingsRepository {
    private final EntityManager entityManager;

    public RatingsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Rating createRating(UUID courseId, UUID userId, RatingCreate ratingCreate) {
        CourseEntity courseEntity = entityManager.getReference(CourseEntity.class, courseId);
        UserEntity userEntity = entityManager.getReference(UserEntity.class, userId);
        RatingEntity ratingEntity = toRatingEntity(courseEntity, userEntity, ratingCreate);

        entityManager.persist(ratingEntity);
        entityManager.flush();

        Rating rating = new Rating(courseId, userId, ratingEntity.getUserRating(), ratingEntity.getMessage());
        return rating;
    }
}
