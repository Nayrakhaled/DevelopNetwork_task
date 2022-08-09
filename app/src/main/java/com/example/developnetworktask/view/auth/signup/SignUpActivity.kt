package com.example.developnetworktask.view.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.developnetworktask.R
import com.example.developnetworktask.databinding.SignupActivityBinding
import com.example.developnetworktask.model.local_source.db.ConcreteLocalSource
import com.example.developnetworktask.model.remote_source.ProductRemoteSource
import com.example.developnetworktask.model.repository.product.ProductRepo
import com.example.developnetworktask.model.repository.user.UserRepo
import com.example.developnetworktask.pojo.User
import com.example.developnetworktask.view.auth.signin.SignInActivity
import com.example.developnetworktask.view.auth.signup.view_model.SignUpVMFactory
import com.example.developnetworktask.view.auth.signup.view_model.SignUpViewModel
import com.example.developnetworktask.view.home.view_model.HomeVMFactory
import com.example.developnetworktask.view.home.view_model.HomeViewModel

class SignUpActivity: AppCompatActivity() {

    private lateinit var binding: SignupActivityBinding
    private lateinit var signUpVM: SignUpViewModel
    private lateinit var signUpVMFactory: SignUpVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignupActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getInit()

    }

    override fun onResume() {
        super.onResume()
        binding.signinTxt.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.signupButton.setOnClickListener {
            val phone = binding.phoneEditText.text.toString()
            val password = binding.passEditText.text.toString()
            val confirmPass = binding.retypePasswordEditText.text.toString()
            val name = binding.displayNameEditText.text.toString()
            checkData(phone, password, confirmPass, name)
        }
    }

    private fun getInit(){
        signUpVMFactory = SignUpVMFactory(
           UserRepo.getInstance(ConcreteLocalSource(this))
        )
        signUpVM = ViewModelProvider(this, signUpVMFactory)[SignUpViewModel::class.java]
    }

    private fun checkData(phone: String, password: String, confirmPass: String, name: String){
        when{
            phone.isEmpty() || password.isEmpty() || confirmPass.isEmpty() || name.isEmpty() ->{
                binding.accountErrorTxt.text = getString(R.string.empty_error)
                binding.accountErrorTxt.visibility = View.VISIBLE
            }
            phone.length != 11 ->{
                binding.accountErrorTxt.text = getString(R.string.phone_error)
                binding.phoneErrorTxt.visibility = View.VISIBLE
            }
            password.length < 8 ->{
                binding.accountErrorTxt.text = getString(R.string.password_error)
                binding.passwordErrorTxt.visibility = View.VISIBLE
            }
            password != confirmPass ->{
                binding.accountErrorTxt.text = getString(R.string.confirm_error)
                binding.confirmPassErrorTxt.visibility = View.VISIBLE
            }
            else ->{
                val token =  List(20) {
                    (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
                }.joinToString("")
                signUpVM.setUser(User(phone, token, password))
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }

    }
}