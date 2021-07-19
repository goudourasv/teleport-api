package com.goudourasv.domain.institutions;

import java.util.UUID;

public class InstitutionData {
    private UUID id;
    private String name;

    public InstitutionData(UUID id,String title){
        this.id = id;
        this.name = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return name;
    }
}

