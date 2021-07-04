package com.goudourasv.http.lectures.dto;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;

public class LectureCreate {
    @NotBlank
    private String title;
    private Instant startTime;
    private Instant endTime;
    private UUID courseId;

    public LectureCreate(String title, Instant startTime, Instant endTime, UUID courseId) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseId = courseId;
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

    public UUID getCourseId() {
        return courseId;
    }
}
