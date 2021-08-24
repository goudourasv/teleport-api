package com.goudourasv.http.institutions.dto;

import java.util.Objects;

public class InstitutionUpdate {
    private String name;
    private String country;
    private String city;


    public InstitutionUpdate() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitutionUpdate that = (InstitutionUpdate) o;
        return name.equals(that.name) && country.equals(that.country) && city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, city);
    }
}
