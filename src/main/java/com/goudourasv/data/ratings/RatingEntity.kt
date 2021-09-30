package com.goudourasv.data.ratings

import com.goudourasv.data.courses.CourseEntity
import com.goudourasv.data.users.UserEntity
import java.util.*
import javax.persistence.*

@Entity(name = "Rating")
class RatingEntity(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    var courseEntity: CourseEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var userEntity: UserEntity,

    @Column
    var message: String,

    @Column(name = "user_rating")
    var userRating: Int,
)
