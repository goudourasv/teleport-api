package com.goudourasv.data.users

import com.goudourasv.domain.users.User
import com.goudourasv.http.users.dto.UserCreate
import com.goudourasv.http.users.dto.UserUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager

@ApplicationScoped
class UsersRepository(private val entityManager: EntityManager) {

    fun getSpecificUser(id: UUID): User {
        val userEntity = entityManager.getReference(UserEntity::class.java, id)
        return userEntity.toUser()
    }

    fun createUser(userCreate: UserCreate): User {
        val userEntity = userCreate.toUserEntity()
        entityManager.persist(userEntity)
        entityManager.flush()

        return userEntity.toUser()
    }

    fun deleteSpecificUser(id: UUID): Boolean {
        val userEntity = entityManager.getReference(UserEntity::class.java, id) ?: return false

        entityManager.remove(userEntity)
        return true
    }

    fun partiallyUpdateUser(id: UUID, userUpdate: UserUpdate): User {
        val userEntity = entityManager.getReference(UserEntity::class.java, id)

        if (userUpdate.firstName != null) {
            userEntity.firstName = userUpdate.firstName!!
        }

        if (userUpdate.lastName != null) {
            userEntity.lastName = userUpdate.lastName!!
        }

        if (userUpdate.email != null) {
            userEntity.email = userUpdate.email!!
        }

        entityManager.merge(userEntity)
        entityManager.flush()

        return userEntity.toUser()


    }
}