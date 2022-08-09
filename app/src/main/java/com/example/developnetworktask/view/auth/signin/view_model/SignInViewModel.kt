package com.example.developnetworktask.view.auth.signin.view_model

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

class SignInViewModel(
    private var iUser: IUserRepo
) : ViewModel() {
    private val _user = MutableLiveData<User>()
    var user: LiveData<User> = _user

    fun getUser(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(iUser.getUser(phone))
        }
    }
}