package com.example.developnetworktask.view.auth.signin.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.developnetworktask.model.repository.product.IProductRepo
import com.example.developnetworktask.model.repository.user.IUserRepo

class SignInVMFactory (
    private var iUser: IUserRepo
        ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            SignInViewModel(iUser) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }

}