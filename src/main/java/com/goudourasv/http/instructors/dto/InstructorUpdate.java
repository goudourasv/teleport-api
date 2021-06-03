package com.goudourasv.http.instructors.dto;

import com.goudourasv.domain.institutions.Institution;

public class InstructorUpdate {
    private String firstName;
    private String lastName;
    private Institution institution;

    public InstructorUpdate(String firstName, String lastName, Institution institution){
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

    public Institution getInstitution() {
        return institution;
    }
}
