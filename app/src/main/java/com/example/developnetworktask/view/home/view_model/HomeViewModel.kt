package com.example.developnetworktask.view.home.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
import com.example.developnetworktask.model.repository.product.IProductRepo
import com.example.developnetworktask.model.repository.product.ProductRepo
import com.example.developnetworktask.pojo.AllProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private var iProducts: IProductRepo
) : ViewModel() {
    private val _allProducts: MutableLiveData<AllProducts> = ProductRepo.allProducts
    var allProducts: LiveData<AllProducts> = _allProducts

    fun getProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            iProducts.getAllProducts()
        }
    }
}