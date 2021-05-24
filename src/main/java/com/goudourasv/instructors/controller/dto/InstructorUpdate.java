package com.goudourasv.instructors.controller.dto;

public class InstructorUpdate {
    private String firstName;
    private String lastName;
    private String institution;

    public InstructorUpdate(String firstName, String lastName, String institution){
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInstitution() {
        return institution;
    }
}
