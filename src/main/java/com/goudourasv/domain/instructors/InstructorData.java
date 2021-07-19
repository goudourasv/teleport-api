package com.goudourasv.domain.instructors;

import java.util.UUID;

public class InstructorData {
    private UUID id;
    private String firstName;
    private String lastName;
    private String name;


    public InstructorData(UUID id,String firstName,String lastName){
        this.id = id;
        this.name = firstName + lastName;
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

    public String getName() {
        return name;
    }
}

