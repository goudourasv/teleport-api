package com.goudourasv.institutions.controller.dto;

public class InstitutionCreate {
    private String name;
    private String country;
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
