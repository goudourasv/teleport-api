package com.goudourasv.data.users

import com.goudourasv.data.courses.CourseEntity
import com.goudourasv.data.ratings.RatingEntity
import java.util.*
import javax.persistence.*

@Entity(name = "Users")
class UserEntity(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "e_mail")
    var email: String,

    @ManyToMany(mappedBy = "favouritedByUsers")
    var courseEntities: MutableSet<CourseEntity>? = mutableSetOf(),

    @OneToMany(mappedBy = "userEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    var ratingEntities: MutableList<RatingEntity>? = mutableListOf(),

)
