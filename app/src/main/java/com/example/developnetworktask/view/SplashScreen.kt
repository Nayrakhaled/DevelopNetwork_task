package com.example.developnetworktask.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.developnetworktask.databinding.ActivitySplashBinding
import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
import com.example.developnetworktask.view.auth.signin.SignInActivity
import com.example.developnetworktask.view.home.HomeActivity

class SplashScreen: AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when {
            SharedPrefs.getInstance(this).getState() == 1 -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            else -> {
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
    }
}