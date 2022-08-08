package com.example.developnetworktask.model.remote_source

import com.example.developnetworktask.pojo.AllProducts

interface IProductRemoteSource {

    suspend fun getAllProducts(token: String) : AllProducts

}