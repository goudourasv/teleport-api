package com.goudourasv.data.courses

import com.goudourasv.data.institutions.InstitutionEntity
import com.goudourasv.data.instructors.InstructorEntity
import com.goudourasv.data.lectures.LectureEntity
import com.goudourasv.data.ratings.RatingEntity
import com.goudourasv.data.tags.TagEntity
import com.goudourasv.data.users.UserEntity
import java.time.Instant
import java.util.*
import javax.persistence.*


// TODO : check how to improve the fetching of the tags when running the below query
@NamedQueries(
    NamedQuery(
        name = "list_live_courses",
        query = """
                         select c from Courses c
                         inner join fetch c.lectureEntities l
                         inner join fetch c.institutionEntity 
                         inner join fetch c.instructorEntity
                         where l.startTime <= :current_timestamp
                         and l.endTime > :current_timestamp
                        """
    ),
    NamedQuery(
        name = "list_favourite_courses",
        query = """
                                        select c from Courses c
                                        inner join c.favouritedByUsers u
                                        where u.id = :user_id
                        """
    )
)

@Entity(name = "Courses")
class CourseEntity(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Column
    var title: String,

    @Column(name = "start_date")
    var startDate: Instant? = null,

    @Column(name = "end_date")
    var endDate: Instant? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    var institutionEntity: InstitutionEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    var instructorEntity: InstructorEntity,

    @OneToMany(mappedBy = "courseEntity", cascade = ([CascadeType.ALL]), orphanRemoval = true)
    var lectureEntities: MutableList<LectureEntity> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "course_tag",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "tag")]
    )
    var tagEntities: MutableSet<TagEntity> = mutableSetOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "favourite_course",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var favouritedByUsers: MutableSet<UserEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "courseEntity", cascade = ([CascadeType.ALL]), orphanRemoval = true)
    var ratingEntities: MutableList<RatingEntity> = mutableListOf(),

    ) {

    fun addUserToFavouritesSet(user: UserEntity) {
        favouritedByUsers.add(user)
    }

    fun deleteUserFromFavouritesSet(user: UserEntity) {
        favouritedByUsers.remove(user)
    }

    fun replaceLectureEntities(lectureEntities: MutableList<LectureEntity>) {
        this.lectureEntities.clear()
        this.lectureEntities.addAll(lectureEntities)
    }

    fun replaceTagEntities(tagEntities: MutableSet<TagEntity>) {
        this.tagEntities.clear()
        this.tagEntities.addAll(tagEntities)

    }


}