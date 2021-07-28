package com.goudourasv.data.users;

import com.goudourasv.domain.users.User;

public class UsersMapper {

    public static User toUser(UserEntity userEntity) {
        User user = new User(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail());
        return user;
    }
}
