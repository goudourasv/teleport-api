package com.goudourasv.institutions.domain;

import com.goudourasv.courses.domain.Course;
import com.goudourasv.professors.Professor;
import com.goudourasv.tags.Tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class Institution {
    private UUID id;
    private final String name;
    private ArrayList<Professor> professors = new ArrayList<Professor>();
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Tag> tags = new ArrayList<Tag>();


    //Constructor
    public Institution (UUID id, String name) {
        this.id = id;
        this.name = name;

    }

    public String getInstitutionName() {
        return name;
    }


    public UUID getId() {
        return id;
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
