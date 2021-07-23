package com.goudourasv.domain.courses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.institutions.InstitutionData;
import com.goudourasv.domain.instructors.InstructorData;
import com.goudourasv.domain.lectures.LectureData;
import com.goudourasv.domain.tags.Tag;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private UUID id;
    private String title;
    private Instant startDate;
    private Instant endDate;
    private Set<Tag> tags;
    @JsonProperty("institution")
    private InstitutionData institutionData;
    @JsonProperty("instructor")
    private InstructorData instructorData;
    @JsonProperty("lectures")
    private List<LectureData> lectureData;


    public Course(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public Course(UUID id, String title, InstitutionData institutionData, Set<Tag> tags, Instant startDate, Instant endDate, List<LectureData> lectureData, InstructorData instructorData) {
        this.id = id;
        this.title = title;
        this.institutionData = institutionData;
        this.tags = tags;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureData = lectureData;
        this.instructorData = instructorData;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public InstitutionData getInstitutionData() {
        return institutionData;
    }

    public InstructorData getInstructorData() {
        return instructorData;
    }

    public List<LectureData> getLectureData() {
        return lectureData;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstitutionData(InstitutionData institutionData) {
        this.institutionData = institutionData;
    }

    public void setInstructorData(InstructorData instructorData) {
        this.instructorData = instructorData;
    }

    public void setLectureData(List<LectureData> lectureData) {
        this.lectureData = lectureData;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



