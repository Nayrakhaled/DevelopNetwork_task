package com.example.developnetworktask.view.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.developnetworktask.R
import com.example.developnetworktask.databinding.SigninActivityBinding
import com.example.developnetworktask.model.local_source.db.ConcreteLocalSource
import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
import com.example.developnetworktask.model.repository.user.UserRepo
import com.example.developnetworktask.pojo.User
import com.example.developnetworktask.view.auth.signin.view_model.SignInVMFactory
import com.example.developnetworktask.view.auth.signin.view_model.SignInViewModel
import com.example.developnetworktask.view.auth.signup.SignUpActivity
import com.example.developnetworktask.view.home.HomeActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: SigninActivityBinding
    private lateinit var signInVM: SignInViewModel
    private lateinit var signInVMFactory: SignInVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SigninActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInit()

    }

    override fun onResume() {
        super.onResume()

        binding.signupTextView.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.signinButton.setOnClickListener {
            val phone = binding.phoneEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            checkData(phone, password)
        }
    }

    private fun getInit() {
        signInVMFactory = SignInVMFactory(
            UserRepo.getInstance(ConcreteLocalSource(this))
        )
        signInVM = ViewModelProvider(this, signInVMFactory)[SignInViewModel::class.java]
    }

    private fun checkData(phone: String, password: String){
        when{
            phone.isEmpty() || password.isEmpty()  ->{
                binding.accountErrorTxt.text = getString(R.string.empty_error)
                binding.accountErrorTxt.visibility = View.VISIBLE
            }
            else ->{

                signInVM.getUser(phone)
                signInVM.user.observe(this){
                    when {
                        it != null -> {
                            SharedPrefs.getInstance(this).setState(1)
                            SharedPrefs.getInstance(this).setToken(it.token!!)
                            startActivity(Intent(this, HomeActivity::class.java))
                        }
                        else -> {
                            binding.accountErrorTxt.text = getString(R.string.account_error)
                            binding.accountErrorTxt.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    }
}