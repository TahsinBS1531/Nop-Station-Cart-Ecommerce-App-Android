package com.example.nopstationcart.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheckoutRetrofitInstance {

    private val BASE_URL = "https://7c0d8715-6f9a-40a1-a263-78b4d5ceb499.mock.pstmn.io/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitInstance(): Retrofit {
        return  retrofit
    }
}