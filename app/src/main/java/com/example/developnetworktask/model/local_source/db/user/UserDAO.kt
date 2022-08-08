package com.example.developnetworktask.model.local_source.db.user


import androidx.room.*
import com.example.developnetworktask.pojo.User

@Dao
interface UserDAO {
    @Query("SELECT * From user WHERE phone LIKE :phone")
    suspend fun getUser(phone: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

}