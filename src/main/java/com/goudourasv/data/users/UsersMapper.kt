package com.goudourasv.data.users

import com.goudourasv.domain.users.User
import com.goudourasv.http.users.dto.UserCreate

fun UserEntity.toUser(): User {
    return User(
        id = this.id!!,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
    )
}
    fun UserCreate.toUserEntity(): UserEntity{
        return UserEntity(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
        )
    }
