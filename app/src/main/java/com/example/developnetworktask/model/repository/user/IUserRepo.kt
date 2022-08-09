package com.example.developnetworktask.model.repository.user

import com.example.developnetworktask.pojo.User

interface IUserRepo {

    suspend fun getUser(phone: String): User
    suspend fun insertUser(user: User)


}