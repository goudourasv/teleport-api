package com.goudourasv.domain.institutions;

import java.util.UUID;

public class InstitutionData {
    private UUID id;
    private String title;

    public InstitutionData(UUID id,String title){
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

