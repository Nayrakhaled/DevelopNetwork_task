package com.example.developnetworktask.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @SerializedName("phone"              ) var phone           : String          ,
    @SerializedName("token"              ) var token           : String?           = null,
    @SerializedName("password"           ) var password        : String?           = null,
)