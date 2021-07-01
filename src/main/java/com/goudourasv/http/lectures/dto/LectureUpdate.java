package com.goudourasv.http.lectures.dto;
import com.goudourasv.domain.courses.Course;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

public class LectureUpdate {
    @NotBlank
    private String title;
    private Instant startTime;
    private Instant endTime;
    private Course course;

    public LectureUpdate(String title,Instant startTime,Instant endTime,Course course){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Course getCourse() {
        return course;
    }
}
