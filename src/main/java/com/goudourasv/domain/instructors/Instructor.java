package com.goudourasv.domain.instructors;

import com.goudourasv.domain.institutions.Institution;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Instructor {
    private UUID id;
    private String firstName;
    private String lastName;
    private List<Institution> institutions;


    public Instructor(UUID id, String firstName, String lastName, List<Institution> institutions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institutions = institutions;
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


    public List<Institution> getInstitutions() {
        return institutions;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
