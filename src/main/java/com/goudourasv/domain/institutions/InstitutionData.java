package com.goudourasv.domain.institutions;

import java.util.UUID;

public class InstitutionData {
    private UUID id;
    private String name;

    public InstitutionData(UUID id,String name){
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return name;
    }
}

