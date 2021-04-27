package com.goudourasv.institutions.controller.dto;

import javax.validation.constraints.NotBlank;

public class InstitutionCreate {
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @NotBlank
    private String city;

    public InstitutionCreate(String name,String country,String city) {
        this.name = name;
        this.country =country;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
