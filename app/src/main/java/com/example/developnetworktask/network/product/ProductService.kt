package com.example.developnetworktask.network.product

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*


interface ProductService {


    @Headers(
        "Content-Type: application/json"
    )
    @GET("{resource}")
    suspend fun getAllProducts(
        @Path("resource", encoded = true) resources: String,
        @Header("token") token: String
    ): Response<JsonObject>

}