package com.goudourasv.domain.lectures;

import com.goudourasv.domain.courses.Course;

import java.time.Instant;
import java.util.UUID;

public class Lecture {
    private UUID id;
    private String title;
    private Instant startTime;
    private Instant endTime;
    private Course course;

    public Lecture(UUID id, String title, Course course, Instant startTime, Instant endTime) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Course getCourse() {
        return course;
    }
}
