package com.goudourasv.courses;

public class Professor {
    private String name;
    private String institution;
    private String title;

    //constructor
    public Professor(String name, String institution, String course) {
    //TODO one professor can teach many courses

        this.name = name;
        this.institution = institution;
        title = course;

    }

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public String getTitle() {
        return title;
    }




}
