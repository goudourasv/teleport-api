package com.goudourasv.professors;

import com.goudourasv.courses.domain.Course;

import java.util.ArrayList;
import java.util.Iterator;

public class Professor {
    private final String name;
    private String institution;
    private ArrayList<Course> courses = new ArrayList<Course>();

    //constructor
    public Professor(String name, String institution) {

        this.name = name;
        this.institution = institution;
    }

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    //Adds a course a professor teaches
    public void addCourse (Course course){
        courses.add(course);
    }

    //List all the courses a professor teaches
    public Iterator<Course> getCourses(){
        return courses.iterator();
    }




}
