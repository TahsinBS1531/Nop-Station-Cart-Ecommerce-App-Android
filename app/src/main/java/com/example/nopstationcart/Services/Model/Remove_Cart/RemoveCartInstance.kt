package com.example.nopstationcart.Services.Model.Remove_Cart

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoveCartInstance {

    fun getInstance(context:Context):Retrofit{
        val client = OkHttpClient.Builder()
            .addInterceptor(RemoveCartAuthInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://demo460.nop-station.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }


}