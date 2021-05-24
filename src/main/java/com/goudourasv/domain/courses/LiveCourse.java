package com.goudourasv.domain.courses;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.lectures.Lecture;

public class LiveCourse {
    private String title;
    private Instructor instructor;
    private Institution institution;
    private Lecture liveLecture;

    public LiveCourse(String title,Instructor instructor,Institution institution,Lecture liveLecture){
        this.title = title;
        this.instructor = instructor;
        this.institution = institution;
        this.liveLecture = liveLecture;

    }


    public String getTitle() {
        return title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Lecture getLiveLecture() {
        return liveLecture;
    }
}
