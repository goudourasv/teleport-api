package com.goudourasv.http.institutions.dto;

public class InstitutionUpdate {
    private String name;
    private String country;
    private String city;

    public InstitutionUpdate(String name, String country, String city) {
        this.name = name;
        this.country = country;
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
