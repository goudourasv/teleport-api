package com.goudourasv.domain.courses;

import java.util.UUID;

public class CourseData {
    private UUID id;
    private String title;

    public CourseData(UUID id,String title){
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
