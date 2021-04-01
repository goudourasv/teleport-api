package com.goudourasv.courses;

public class Institution {
    //Constructor
    public Institution(String name, String professor, String course, String field) {
        //TODO should pass multiple professors here


        this.name = name;
        professorName = professor;
        courseName = course;
        tagName = field;
    }

    public String getInstitutionName() {
        return name;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTagName() {
        return tagName;
    }

    private final String name;
    private final String professorName;
    private final String courseName;
    private final String tagName;
}
