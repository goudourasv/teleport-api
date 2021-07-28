package com.goudourasv.domain.users;

import com.goudourasv.data.users.UsersRepository;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.http.users.dto.FavouriteCourseCreate;
import com.goudourasv.http.users.dto.UserCreate;
import com.goudourasv.http.users.dto.UserUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UsersService {
    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public User getSpecificUser(UUID id) {
        User user = usersRepository.getSpecificUser(id);
        return user;
    }

    @Transactional
    public User createNewUser(UserCreate userCreate) {
        User user = usersRepository.createUser(userCreate);
        return user;
    }

    @Transactional
    public boolean deleteSpecificUser(UUID id) {
        boolean deleted = usersRepository.deleteSpecificUser(id);
        return deleted;
    }

    @Transactional
    public User partiallyUpdateUser(UUID id, UserUpdate userUpdate) {
        User user = usersRepository.partiallyUpdateUser(id, userUpdate);
        return user;
    }

    @Transactional
    public Course createFavourite(FavouriteCourseCreate favouriteCourseCreate) {
        Course favouriteCourse = usersRepository.createFavouriteCourse(favouriteCourseCreate);
        return favouriteCourse;

    }

//    public List<FavouriteCourse> getFavouriteCourses(UUID userId) {
//        List<FavouriteCourse> favouriteCourses = usersRepository.getFavouriteCourses(userId);
//        return favouriteCourses;
//    }
}
