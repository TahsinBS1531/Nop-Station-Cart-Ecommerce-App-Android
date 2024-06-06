package com.example.nopstationcart.Services.Model.Cart

import android.content.Context
import com.example.nopstationcart.Services.Netwrok.AddToCartApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CartretrofitInstance {

    fun getretrofit(context:Context):Retrofit{
        val client = OkHttpClient.Builder()
            .addInterceptor(CartAuthInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://demo460.nop-station.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //val addToCartApi = retrofit.create(AddToCartApiInterface::class.java)
        return retrofit
    }


}