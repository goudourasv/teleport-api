package com.goudourasv.domain.users;

import javax.validation.constraints.Email;
import java.util.UUID;

public class UserData {
    private UUID id;
    private String firstName;
    private String lastName;
    @Email
    private String email;


    public UserData(UUID id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
