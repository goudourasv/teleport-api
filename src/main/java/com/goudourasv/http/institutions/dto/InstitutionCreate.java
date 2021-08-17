package com.goudourasv.http.institutions.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class InstitutionCreate {
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @NotBlank
    private String city;

    private List<UUID> instructorIds;


    public InstitutionCreate(String name, String country, String city, List<UUID> instructorIds) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.instructorIds = instructorIds;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public List<UUID> getInstructorIds() {
        return instructorIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitutionCreate that = (InstitutionCreate) o;
        return instructorIds.equals(that.instructorIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorIds);
    }
}
