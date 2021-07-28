package com.goudourasv.http.users.dto;

import java.util.UUID;

public class FavouriteCourseCreate {
    private UUID courseId;
    private UUID userId;

    public FavouriteCourseCreate() {
    }

    public FavouriteCourseCreate(UUID courseId) {
        this.courseId = courseId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
