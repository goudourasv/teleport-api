package com.goudourasv.professors.controller.dto;

import javax.validation.constraints.NotBlank;

public class ProfessorCreate {
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    private String institution;

    public ProfessorCreate(String firstName,String lastName,String institution){
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getInstitution() {
        return institution;
    }
}
