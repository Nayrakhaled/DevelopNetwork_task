package com.example.developnetworktask.model.local_source.sharedPrefs


interface ISharedPrefs {

    fun setToken(token: String)
    fun getToken(): String

    fun setState(state: Int)
    fun getState(): Int

}