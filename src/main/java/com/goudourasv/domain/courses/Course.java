package com.goudourasv.domain.courses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.lectures.Lecture;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private UUID id;
    private String title;
    @JsonProperty("institution")
    private String institutionName;
    private String tag;
    private String instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Lecture> lectures = new ArrayList<>();


    public Course(UUID id, String title, String institution, String tag, String instructor) {
        this.id = id;
        this.title = title;
        institutionName = institution;
        this.tag = tag;
        this.instructor = instructor;
        System.out.println("yes");
    }

    public Course(String title, String institution, String tag, String instructor) {
        this.title = title;
        institutionName = institution;
        this.tag = tag;
        this.instructor = instructor;
        System.out.println("yes no");

    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }


    public String getInstitutionName() {
        return institutionName;
    }

    public String getTag() {
        return tag;
    }

    public String getInstructor() {
        return instructor;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    //Adds a lecture in a course
    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public String setTag(String tag) {
        this.tag = tag;
        return tag;
    }

    public String setInstructor(String instructor) {
        this.instructor = instructor;
        return instructor;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }

    public String setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
        return institutionName;
    }


    public void generateId() {
        UUID id = UUID.randomUUID();
        this.id = id;
    }
}



