package com.goudourasv.professors.controller.dto;

public class ProfessorUpdate {
    private String firstName;
    private String lastName;
    private String institution;

    public ProfessorUpdate(String firstName,String lastName,String institution){
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
