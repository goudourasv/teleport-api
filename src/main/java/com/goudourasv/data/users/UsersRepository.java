package com.goudourasv.data.users;

import com.goudourasv.domain.users.User;
import com.goudourasv.http.users.dto.UserCreate;
import com.goudourasv.http.users.dto.UserUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.UUID;

import static com.goudourasv.data.users.UsersMapper.toUser;


@ApplicationScoped
public class UsersRepository {
    private EntityManager entityManager;

    public UsersRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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
}
