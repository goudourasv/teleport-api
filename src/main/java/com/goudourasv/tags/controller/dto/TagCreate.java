package com.goudourasv.tags.controller.dto;

import javax.validation.constraints.NotBlank;

public class TagCreate {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
