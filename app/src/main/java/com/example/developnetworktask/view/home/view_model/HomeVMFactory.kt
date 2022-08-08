package com.example.developnetworktask.view.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.developnetworktask.model.repository.product.IProductRepo

class HomeVMFactory (
    private var iProducts: IProductRepo
        ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(iProducts) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }

}