package com.goudourasv.http.instructors.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

public class InstructorCreate {
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    @JsonProperty("institutions")
    private List<UUID> institutionIds;


    public InstructorCreate(String firstName, String lastName, List<UUID> institutionIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.institutionIds = institutionIds;

    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<UUID> getInstitutionIds() {
        return institutionIds;
    }
}
