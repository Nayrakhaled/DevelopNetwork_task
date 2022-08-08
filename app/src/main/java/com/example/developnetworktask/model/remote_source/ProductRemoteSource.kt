package com.example.developnetworktask.model.remote_source

import com.example.developnetworktask.model.Keys
import com.example.developnetworktask.network.product.ProductService
import com.example.developnetworktask.network.product.RetrofitHelper
import com.example.developnetworktask.pojo.AllProducts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductRemoteSource  private constructor(): IProductRemoteSource {
    private val gson = Gson()

    private val api: ProductService =
        RetrofitHelper.getInstance().create(ProductService::class.java)

    companion object {
        private var remoteSource: ProductRemoteSource? = null
        fun getInstance(): ProductRemoteSource {
            return remoteSource ?: ProductRemoteSource()
        }
    }

    override suspend fun getAllProducts(token: String): AllProducts {
        val res = api.getAllProducts(Keys.PRODUCTS, token)
        return gson.fromJson(
            res.body()!!,
            object : TypeToken<AllProducts>() {}.type
        )
    }
}