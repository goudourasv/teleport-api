package com.goudourasv.courses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;

public class Tag {

    private String tag;
    private ArrayList<Course> courses = new ArrayList<Course>();

    //Constructor
    public Tag(String field) {
        tag = field;
    }

    public String getTag() {
        return tag;
    }

    //Adds a course in a tag
    public void addCourse (Course course){
        courses.add(course);
    }

    //List all the courses of the specific tag
    public Iterator<Course> getCourses(){
        return courses.iterator();
    }

    public String toString(){
        return ("");
    }


}





