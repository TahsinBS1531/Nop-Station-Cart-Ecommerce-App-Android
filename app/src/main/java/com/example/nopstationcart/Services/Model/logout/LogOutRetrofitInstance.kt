package com.example.nopstationcart.Services.Model.logout

import android.content.Context
import com.example.nopstationcart.Services.Model.Cart.CartAuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogOutRetrofitInstance(private val context:Context) {

        val client = OkHttpClient.Builder()
            .addInterceptor(logOutAuthInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://demo460.nop-station.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}