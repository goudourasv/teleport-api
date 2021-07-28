package com.goudourasv.http.users.dto;

import java.util.UUID;

public class FavouriteCreate {
    private UUID courseId;
    private UUID userId;

    public FavouriteCreate(UUID courseId, UUID userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
