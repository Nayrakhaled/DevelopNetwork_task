package com.example.developnetworktask.model.repository.user

import com.example.developnetworktask.model.local_source.db.LocalSource
import com.example.developnetworktask.pojo.User


class UserRepo private constructor(
    private var localSource: LocalSource
) :
    IUserRepo {

    companion object {
        private val userRepo: UserRepo? = null

        fun getInstance(localSource: LocalSource): UserRepo {
            return userRepo ?: UserRepo(localSource)
        }
    }

    override suspend fun getUser(phone: String): User {
        return localSource.getUser(phone)
    }

    override suspend fun insertUser(user: User) {
        localSource.insertUser(user)
    }

}