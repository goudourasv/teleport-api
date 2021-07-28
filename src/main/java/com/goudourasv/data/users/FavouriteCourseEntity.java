package com.goudourasv.data.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "favourites")
public class FavouriteCourseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "course_id")
    private UUID courseId;
    @Column (name = "user_id")
    private UUID userId;

    public UUID getId() {
        return id;
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
