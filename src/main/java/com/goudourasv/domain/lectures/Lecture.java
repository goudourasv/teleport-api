package com.goudourasv.domain.lectures;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CourseLecture;

import java.time.Instant;
import java.util.UUID;

public class Lecture {
    private UUID id;
    private String title;
    private Instant startTime;
    private Instant endTime;
    private CourseLecture course;

    public Lecture(UUID id, String title, CourseLecture course, Instant startTime, Instant endTime) {
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

    public CourseLecture getCourse() {
        return course;
    }
}
