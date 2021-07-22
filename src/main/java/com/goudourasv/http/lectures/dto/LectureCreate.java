package com.goudourasv.http.lectures.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.time.Instant;
import java.util.UUID;

public class LectureCreate {
    @NotBlank
    private String title;
//    @URL
    private URI uri;
    private Instant startTime;
    private Instant endTime;
    private UUID courseId;

    public LectureCreate(String title, URI uri, Instant startTime, Instant endTime, UUID courseId) {
        this.title = title;
        this.uri = uri;
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

    public URI getUri() {
        return uri;
    }
}
