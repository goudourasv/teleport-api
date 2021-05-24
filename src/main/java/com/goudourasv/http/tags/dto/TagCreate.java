package com.goudourasv.http.tags.dto;

import javax.validation.constraints.NotBlank;

public class TagCreate {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
