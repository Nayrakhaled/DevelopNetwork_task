package com.example.developnetworktask.model.local_source.db

import com.example.developnetworktask.pojo.User


interface LocalSource {
    fun insertUser(user: User)
    suspend fun getUser(phone: String): User

}