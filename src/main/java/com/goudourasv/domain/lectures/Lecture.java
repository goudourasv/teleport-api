package com.goudourasv.domain.lectures;

import com.goudourasv.domain.courses.CourseLecture;
import org.hibernate.validator.constraints.URL;

import java.net.URI;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;


//TODO Create URL property for lectures to link with (ex.ZOOM APP)
public class Lecture {
    private UUID id;
    private String title;
    @URL
    private URI uri;
    private Instant startTime;
    private Instant endTime;
    private CourseLecture course;

    public Lecture(UUID id, String title, URI uri, CourseLecture course, Instant startTime, Instant endTime) {
        this.id = id;
        this.title = title;
        this.uri = uri;
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

    public URI getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return id.equals(lecture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
