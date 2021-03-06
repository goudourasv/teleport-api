package com.goudourasv.data.users;

import com.goudourasv.data.ratings.RatingEntity;
import com.goudourasv.data.courses.CourseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "e_mail")
    private String email;

    @ManyToMany(mappedBy = "favouritedByUsers")
    private Set<CourseEntity> courseEntities;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RatingEntity> ratingEntities;


    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
