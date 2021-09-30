package com.goudourasv.domain.users

import com.goudourasv.data.users.UsersRepository
import com.goudourasv.http.users.dto.UserCreate
import com.goudourasv.http.users.dto.UserUpdate
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class UsersService(private val usersRepository: UsersRepository) {

    @Transactional
    fun getSpecificUser(id: UUID): User {
        return usersRepository.getSpecificUser(id)
    }

    @Transactional
    fun createNewUser(userCreate: UserCreate): User {
        return usersRepository.createUser(userCreate)
    }

    @Transactional
    fun deleteSpecificUser(id: UUID): Boolean {
        val deleted = usersRepository.deleteSpecificUser(id)
        return deleted
    }

    @Transactional
    fun partiallyUpdateUser(id: UUID, userUpdate: UserUpdate): User {
        return usersRepository.partiallyUpdateUser(id,userUpdate)
    }
}
