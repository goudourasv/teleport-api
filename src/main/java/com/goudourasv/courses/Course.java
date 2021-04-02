package com.goudourasv.courses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Course {

    private final String title;
    private final String institutionName;
    private String tag;
    private String professor;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Lecture> lectures = new ArrayList<Lecture>();


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
    //Adds a lecture in a course
    public void addLecture (Lecture lecture){
        lectures.add(lecture);
    }

    //List all the lectures of the course
    public Iterator<Lecture> getLectures(){
        return lectures.iterator();
    }

}
