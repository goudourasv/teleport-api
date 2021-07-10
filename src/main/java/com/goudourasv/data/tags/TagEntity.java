package com.goudourasv.data.tags;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Tags")
public class TagEntity {
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
