package com.goudourasv.http.instructors.dto;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;

import javax.validation.constraints.NotBlank;

public class InstructorCreate {
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;

    private Institution institution;


    public InstructorCreate(String firstName, String lastName, Institution institution) {
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

    public Institution getInstitution() {
        return institution;
    }
}
