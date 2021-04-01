package com.goudourasv.courses;

import java.time.LocalDate;

public class Course {

    private final String title;
    private final String institutionName;
    private String tag;
    private String professor;
    private LocalDate startDate;
    private LocalDate endDate;


    //Constructor
    public Course(String title, String institution, String tag, String professor) {
        this.title = title;
        institutionName = institution;
        this.tag = tag;
        this.professor = professor;

    }

    public String getTitle() {
        return title;
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
}
