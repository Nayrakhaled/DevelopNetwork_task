package com.example.developnetworktask.view.auth.signup.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.developnetworktask.model.repository.product.IProductRepo
import com.example.developnetworktask.model.repository.user.IUserRepo

class SignUpVMFactory (
    private var iUser: IUserRepo
        ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            SignUpViewModel(iUser) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }

}