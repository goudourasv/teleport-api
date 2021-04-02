package com.goudourasv.institutions;

import com.goudourasv.courses.Course;
import com.goudourasv.courses.Professor;
import com.goudourasv.courses.Tag;

import java.util.ArrayList;
import java.util.Iterator;

public class Institution {

    private final String name;
    private ArrayList<Professor> professors = new ArrayList<Professor>();
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Tag> tags = new ArrayList<Tag>();


    //Constructor
    public Institution(String name) {

        this.name = name;

    }

    public String getInstitutionName() {
        return name;
    }


    //Adds a course in an institution
    public void addCourse (Course course){
        courses.add(course);
    }

    //List all the courses of the specific institution
    public Iterator<Course> getCourses(){
        return courses.iterator();
    }

    //Adds a professor in an institution
    public void addProfessor (Professor professor){
        professors.add(professor);
    }

    //List all the professors of the specific institution
    public Iterator<Professor> getProfessors(){
        return professors.iterator();
    }

    //Adds a tag in an institution
    public void addTag (Tag field){
        tags.add(field);
    }

    //List all the fields of the specific institution
    public Iterator<Tag> getTags(){
        return tags.iterator();
    }

    public String toString(){
        return ("");
    }

}
