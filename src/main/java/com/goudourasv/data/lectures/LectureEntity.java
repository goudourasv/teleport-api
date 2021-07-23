package com.goudourasv.data.lectures;

import com.goudourasv.data.courses.CourseEntity;

import javax.persistence.*;
import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "Lectures")
public class LectureEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String title;
    @Column(name = "URI")
//    @URL
    private URI uri;
    @Column(name = "start_time")
    private Instant startTime;
    @Column(name = "end_time")
    private Instant endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;

    public LectureEntity() {

    }

    public LectureEntity(UUID id, String title, URI uri, Instant startTime, Instant endTime) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public UUID getId() {
        return id;
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

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public URI getUri() {
        return uri;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}

