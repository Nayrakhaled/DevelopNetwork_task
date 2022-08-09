package com.example.developnetworktask.model.repository.product

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
//import com.example.developnetworktask.model.local_source.sharedPrefs.SharedPrefs
import com.example.developnetworktask.model.remote_source.IProductRemoteSource
import com.example.developnetworktask.pojo.AllProducts


class ProductRepo private constructor(
    private var remoteSource: IProductRemoteSource,
    private val context: Context
) :
    IProductRepo {

    companion object {
        private val productRepo: ProductRepo? = null
        val allProducts = MutableLiveData<AllProducts>()

        fun getInstance(remoteSource: IProductRemoteSource, context: Context): ProductRepo {
            return productRepo ?: ProductRepo(remoteSource, context)
        }
    }


    override suspend fun getAllProducts() {
        allProducts.postValue(
            remoteSource.getAllProducts(
                SharedPrefs.getInstance(context).getToken()
            )
        )
    }

}