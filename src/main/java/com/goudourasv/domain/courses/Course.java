package com.goudourasv.domain.courses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.tags.Tag;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private UUID id;
    private String title;
    @JsonProperty("institution")
    private Institution institution;
    private List<Tag> tags;
    private Instructor instructor;
    private Instant startDate;
    private Instant endDate;
    private List<Lecture> lectures = new ArrayList<>();


    public Course(UUID id, String title, Institution institution, List<Tag> tags, Instructor instructor, Instant startDate, Instant endDate) {
        this.id = id;
        this.title = title;
        this.institution = institution;
        this.tags = tags;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
    }

    public Course(String title, Institution institution,List<Tag> tags, Instructor instructor) {
        this.title = title;
        this.institution = institution;
        this.tags = tags;
        this.instructor = instructor;

    }

    public Course(UUID id, String title) {
        this.id = id;
        this.title = title;
    }
    public Course(UUID id, String title, Institution institution, List<Tag> tags, Instructor instructor, Instant startDate, Instant endDate,List<Lecture> lectures) {
        this.id = id;
        this.title = title;
        this.institution = institution;
        this.tags = tags;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
        this.lectures = lectures;
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }


    public Institution getInstitution() {
        return institution;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public Instructor setInstructor(Instructor instructor) {
        this.instructor = instructor;
        return instructor;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }

    public Institution setInstitution(Institution institution) {

        this.institution = institution;
        return institution;
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



