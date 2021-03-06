package com.goudourasv.domain.users;

import com.goudourasv.data.users.UsersRepository;
import com.goudourasv.http.users.dto.UserCreate;
import com.goudourasv.http.users.dto.UserUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
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
}
