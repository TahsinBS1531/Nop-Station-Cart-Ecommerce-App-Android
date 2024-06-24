package com.example.nopstationcart.Services.Model.Update_Cart

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UpdateCartInstance {
    fun getRetrofit(context:Context):Retrofit{
        val client = OkHttpClient.Builder()
            .addInterceptor(UpdateCartAuthInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://demo460.nop-station.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }


}