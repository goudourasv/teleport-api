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
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private UUID id;
    private String title;
    @JsonProperty("institution")
    private Institution institution;
    private Tag tag;
    private Instructor instructor;
    private Instant startDate;
    private Instant endDate;
    private List<Lecture> lectures = new ArrayList<>();



    public Course(UUID id, String title, Institution institution, Tag tag, Instructor instructor,Instant startDate,Instant endDate) {
        this.id = id;
        this.title = title;
        this.institution = institution;
        this.tag = tag;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
    }

    public Course(String title, Institution institution, Tag tag, Instructor instructor) {
        this.title = title;
        this.institution = institution;
        this.tag = tag;
        this.instructor = instructor;

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

    public Tag getTag() {
        return tag;
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

    //Adds a lecture in a course
    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public Tag setTag(Tag tag) {
        this.tag = tag;
        return tag;
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


    public void generateId() {
        UUID id = UUID.randomUUID();
        this.id = id;
    }
}



