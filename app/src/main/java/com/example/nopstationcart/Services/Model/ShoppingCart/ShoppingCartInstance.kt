package com.example.nopstationcart.Services.Model.ShoppingCart

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ShoppingCartInstance {
    val client = OkHttpClient.Builder()
        .addInterceptor(ShoppingCartAuthInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://demo460.nop-station.com/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}