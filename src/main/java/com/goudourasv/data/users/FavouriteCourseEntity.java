package com.goudourasv.data.users;

import com.goudourasv.data.courses.CourseEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "favourites")
public class FavouriteCourseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @MapsId("course_id")
    private CourseEntity course;
    @ManyToOne
    @MapsId("user_id")
    private UserEntity user;

    public UUID getId() {
        return id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
