package com.goudourasv.domain.institutions;


import java.util.Objects;
import java.util.UUID;


public class Institution {
    private String city;
    private String country;
    private UUID id;
    private String name;

    public Institution() {

    }

    public Institution(UUID id, String name) {
        this.id = id;
        this.name = name;

    }

    public Institution(UUID id, String name, String country, String city) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public Institution(String name, String country, String city) {
        this.generateNewId();
        this.name = name;
        this.country = country;
        this.city = city;

    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }


    public UUID getId() {
        return id;
    }

    public void generateNewId() {
        UUID id = UUID.randomUUID();
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


