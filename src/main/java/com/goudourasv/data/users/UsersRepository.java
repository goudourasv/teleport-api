package com.goudourasv.data.users;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.users.FavouriteCourse;
import com.goudourasv.domain.users.User;
import com.goudourasv.http.users.dto.FavouriteCreate;
import com.goudourasv.http.users.dto.UserCreate;
import com.goudourasv.http.users.dto.UserUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.goudourasv.data.users.UsersMapper.toUser;


@ApplicationScoped
public class UsersRepository {
    private EntityManager entityManager;
    private CoursesRepository coursesRepository;

    public UsersRepository(EntityManager entityManager, CoursesRepository coursesRepository) {
        this.entityManager = entityManager;
        this.coursesRepository = coursesRepository;
    }


    public User getSpecificUser(UUID id) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        User user = toUser(userEntity);
        return user;
    }

    public User createUser(UserCreate userCreate) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userCreate.getFirstName());
        userEntity.setLastName(userCreate.getLastName());

        entityManager.persist(userEntity);
        entityManager.flush();

        User user = toUser(userEntity);
        return user;
    }

    public boolean deleteSpecificUser(UUID id) {
        UserEntity userEntity = entityManager.getReference(UserEntity.class, id);
        if (userEntity == null) {
            return false;
        }
        entityManager.remove(userEntity);
        return true;
    }


    public User partiallyUpdateUser(UUID id, UserUpdate userUpdate) {
        UserEntity userEntity = entityManager.getReference(UserEntity.class, id);

        if (userUpdate.getFirstName() != null) {
            String newUserFirstName = userUpdate.getFirstName();
            userEntity.setFirstName(newUserFirstName);
        }

        if (userUpdate.getLastName() != null) {
            String newUserLastName = userUpdate.getLastName();
            userEntity.setFirstName(newUserLastName);
        }

        if (userUpdate.getEmail() != null) {
            String newUserEmail = userUpdate.getEmail();
            userEntity.setEmail(newUserEmail);
        }

        entityManager.merge(userEntity);
        entityManager.flush();

        User user = toUser(userEntity);
        return user;
    }

    public FavouriteCourse createFavouriteCourse(FavouriteCreate favouriteCreate) {
        FavouriteCourseEntity favouriteCourseEntity = new FavouriteCourseEntity();
        favouriteCourseEntity.setCourseId(favouriteCreate.getCourseId());
        favouriteCourseEntity.setUserId(favouriteCreate.getUserId());

        entityManager.persist(favouriteCourseEntity);
        entityManager.flush();

        Course course = coursesRepository.getSpecificCourse(favouriteCourseEntity.getCourseId());
        User user = getSpecificUser(favouriteCourseEntity.getUserId());

        FavouriteCourse favouriteCourse = new FavouriteCourse(favouriteCourseEntity.getId(), course, user);
        return favouriteCourse;
    }

    public List<FavouriteCourse> getFavouriteCourses(UUID userId) {
        String sqlQuery = "SELECT * FROM favourites WHERE user_id = :userId";
        @SuppressWarnings("unchecked")
        List<FavouriteCourseEntity> favouriteCourseEntities = entityManager.createNativeQuery(sqlQuery,FavouriteCourseEntity.class).setParameter("userId",userId).getResultList();
        List<FavouriteCourse> favouriteCourses = new ArrayList<>();
        for(FavouriteCourseEntity favouriteCourseEntity : favouriteCourseEntities){
            Course course =  coursesRepository.getSpecificCourse(favouriteCourseEntity.getCourseId());
            User user = getSpecificUser(favouriteCourseEntity.getUserId());
            FavouriteCourse favouriteCourse = new FavouriteCourse(favouriteCourseEntity.getId(),course,user);
            favouriteCourses.add(favouriteCourse);
        }
        return favouriteCourses;
    }
}
