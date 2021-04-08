package com.goudourasv.courses.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.courses.Lecture;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private final int id;
    private final String title;
    @JsonProperty("institution")
    private final String institutionName;
    private String tag;
    private String professor;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Lecture> lectures = new ArrayList<Lecture>();


    public Course() {
        id = 0;
        title = null;
        institutionName = null;
    }

    //Constructor
    public Course(int id, String title, String institution, String tag, String professor) {
        this.id = id;
        this.title = title;
        institutionName = institution;
        this.tag = tag;
        this.professor = professor;

    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }


    public String getInstitutionName() {
        return institutionName;
    }

    public String getTag() {
        return tag;
    }

    public String getProfessor() {
        return professor;
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

}
