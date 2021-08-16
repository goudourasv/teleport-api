package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.lectures.Lecture;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class CourseCreate {
    @NotBlank
    private String title;
    private Instant startDate;
    private Instant endDate;
    @JsonProperty("institution")
    private UUID institutionId;
    @JsonProperty("instructor")
    private UUID instructorId;
    private List<String> tags = new ArrayList<>();
    @JsonProperty("lectures")
    private List<Lecture> lectures;

    public CourseCreate() {
    }

    public CourseCreate(String title, UUID institutionId, List<String> tags, UUID instructorId, Instant startDate, Instant endDate, List<Lecture> lectures) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.institutionId = institutionId;
        this.instructorId = instructorId;
        if (tags != null) {
            this.tags = tags;
        }
        if (lectures != null) {
            this.lectures = lectures;
        }
    }

    public String getTitle() {
        return title;
    }

    public UUID getInstitutionId() {
        return institutionId;
    }

    public List<String> getTags() {
        return tags;
    }

    public UUID getInstructorId() {
        return instructorId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCreate that = (CourseCreate) o;
        return title.equals(that.title) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(institutionId, that.institutionId) && Objects.equals(instructorId, that.instructorId) && Objects.equals(tags, that.tags) && Objects.equals(lectures, that.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, endDate, institutionId, instructorId, tags, lectures);
    }
}
