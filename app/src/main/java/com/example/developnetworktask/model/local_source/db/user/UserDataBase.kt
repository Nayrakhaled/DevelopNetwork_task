package com.example.developnetworktask.model.local_source.db.user

import android.content.Context
import androidx.room.*
import com.example.developnetworktask.pojo.User
import kotlin.jvm.Synchronized

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        private var INSTANCE: UserDataBase? = null
        @Synchronized
        fun getInstance(context: Context): UserDataBase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                UserDataBase::class.java,
                "users"
            ).fallbackToDestructiveMigration().build()
        }
    }
}