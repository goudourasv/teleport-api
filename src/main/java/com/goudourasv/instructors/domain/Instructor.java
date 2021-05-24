package com.goudourasv.instructors.domain;

import java.util.UUID;

public class Instructor {
    private UUID id;
    private String firstName;
    private String lastName;
    private String institution;


    public Instructor(UUID id, String firstName, String lastName, String institution) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
    }

    public Instructor(String firstName, String lastName, String institution) {
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


    public String getInstitution() {
        return institution;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
