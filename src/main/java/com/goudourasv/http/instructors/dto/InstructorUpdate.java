package com.goudourasv.http.instructors.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class InstructorUpdate {
    private String firstName;
    private String lastName;
    @JsonProperty("institutions")
    private List<UUID> institutionIds;

    public InstructorUpdate(String firstName, String lastName, List<UUID> institutionIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.institutionIds = institutionIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<UUID> getInstitutionIds() {
        return institutionIds;
    }
}
