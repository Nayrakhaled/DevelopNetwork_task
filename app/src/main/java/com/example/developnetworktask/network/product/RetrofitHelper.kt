package com.example.developnetworktask.network.product

import com.example.developnetworktask.model.Keys
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//                var client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(Keys.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                   // .client(client)
                    .build()
            }
            return retrofit!!
        }
    }
}