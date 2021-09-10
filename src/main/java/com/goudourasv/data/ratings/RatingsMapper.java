package com.goudourasv.data.ratings;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.data.users.UserEntity;
import com.goudourasv.http.courses.dto.RatingCreate;

public class RatingsMapper {
    public static RatingEntity toRatingEntity(CourseEntity courseEntity, UserEntity userEntity, RatingCreate ratingCreate) {
        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setCourseEntity(courseEntity);
        ratingEntity.setUserEntity(userEntity);
        ratingEntity.setUserRating(ratingCreate.getUserRating());
        ratingEntity.setMessage(ratingCreate.getMessage());
        return ratingEntity;

    }
}
