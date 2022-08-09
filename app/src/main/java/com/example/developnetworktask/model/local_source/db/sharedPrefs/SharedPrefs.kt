package com.example.developnetworktask.model.local_source.sharedPrefs

import android.app.Activity
import android.content.Context

import android.content.SharedPreferences
import com.example.developnetworktask.model.Keys
import java.security.Key


class SharedPrefs private constructor() : ISharedPrefs {
    companion object {
        private var sharedPref: SharedPrefs = SharedPrefs()
        private lateinit var sharedPreferences: SharedPreferences
        private var editor: SharedPreferences.Editor? = null


        fun getInstance(context: Context): SharedPrefs {
            if (!::sharedPreferences.isInitialized) {
                synchronized(SharedPrefs::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences = context.getSharedPreferences(Keys.USER, Activity.MODE_PRIVATE)
                        editor =  sharedPreferences.edit()
                    }
                }
            }
            return sharedPref
        }
    }

    override fun setToken(token: String) {
        editor!!.putString(Keys.TOKEN, token)
        editor!!.commit()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(Keys.TOKEN, null)!!
    }

    override fun setState(state: Int) {
        editor!!.putInt(Keys.IS_LOGGED, state)
        editor!!.commit()
    }

    override fun getState(): Int {
        return sharedPreferences.getInt(Keys.IS_LOGGED, -1)
    }


}