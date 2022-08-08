package com.example.developnetworktask.model.local_source.db

import android.content.Context
import com.example.developnetworktask.model.local_source.db.user.UserDAO
import com.example.developnetworktask.model.local_source.db.user.UserDataBase
import com.example.developnetworktask.pojo.User


class ConcreteLocalSource (context: Context) : LocalSource {
    private val dao: UserDAO?


    init {
        val db: UserDataBase = UserDataBase.getInstance(context)
        dao = db.userDAO()

    }

    override fun insertUser(user: User) {
        dao?.insertUser(user)
    }

    override suspend fun getUser(phone: String): User {
       return dao!!.getUser(phone)
    }

}