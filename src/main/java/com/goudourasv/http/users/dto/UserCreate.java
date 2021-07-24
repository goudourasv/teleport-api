package com.goudourasv.http.users.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserCreate {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;


    public UserCreate(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
