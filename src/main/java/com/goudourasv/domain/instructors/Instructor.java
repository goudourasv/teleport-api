package com.goudourasv.domain.instructors;

import com.goudourasv.domain.institutions.Institution;

import java.util.UUID;

public class Instructor {
    private UUID id;
    private String firstName;
    private String lastName;
    private Institution institution;


    public Instructor(UUID id, String firstName, String lastName,Institution institution) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
    }

    public Instructor(String firstName, String lastName, Institution institution) {
        this.generateNewId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
    }

    private void generateNewId() {
        UUID id = UUID.randomUUID();
        this.id = id;
    }


    public UUID getId() {
        return id;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
