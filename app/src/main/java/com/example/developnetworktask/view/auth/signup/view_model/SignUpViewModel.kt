package com.example.developnetworktask.view.auth.signup.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
import com.example.developnetworktask.model.repository.product.IProductRepo
import com.example.developnetworktask.model.repository.product.ProductRepo
import com.example.developnetworktask.model.repository.user.IUserRepo
import com.example.developnetworktask.pojo.AllProducts
import com.example.developnetworktask.pojo.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val iUser: IUserRepo
) : ViewModel() {


    fun setUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            iUser.insertUser(user)
        }
    }
}