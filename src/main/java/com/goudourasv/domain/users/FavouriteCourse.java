package com.goudourasv.domain.users;

import com.goudourasv.domain.courses.Course;

import java.util.UUID;

public class FavouriteCourse {
    private UUID id;
    private Course course;
    private User user;

    public FavouriteCourse(UUID id, Course course, User user) {
        this.id = id;
        this.course = course;
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public User getUser() {
        return user;
    }
}
