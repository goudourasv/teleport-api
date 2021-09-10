package com.goudourasv.domain.ratings;

import java.util.UUID;

public class Rating {
    private UUID courseId;
    private UUID userId;
    private int userRating;
    private String message;

    public Rating(UUID courseId, UUID userId, int userRating, String message) {
        this.courseId = courseId;
        this.userId = userId;
        this.userRating = userRating;
        this.message = message;
    }

    public Rating() {
    }

    public UUID getCourseId() {
        return courseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getUserRating() {
        return userRating;
    }

    public String getMessage() {
        return message;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
