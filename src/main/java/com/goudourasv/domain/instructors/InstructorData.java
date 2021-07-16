package com.goudourasv.domain.instructors;

import java.util.UUID;

public class InstructorData {
    private UUID id;
    private String title;

    public InstructorData(UUID id,String title){
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

