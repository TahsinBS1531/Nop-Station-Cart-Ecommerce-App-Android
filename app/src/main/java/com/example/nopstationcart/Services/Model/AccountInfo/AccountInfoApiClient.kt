package com.example.nopstationcart.Services.Model.AccountInfo

import android.content.Context
import com.example.nopstationcart.Services.Model.login.loginAuthInterceptor
import com.example.nopstationcart.Services.Netwrok.AccountInfoApiInterface
import com.example.nopstationcart.Services.Netwrok.loginApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AccountInfoApiClient(private val context:Context) {

        private val BASE_URL = "http://demo460.nop-station.com/api/"
        val client = OkHttpClient.Builder()
            .addInterceptor(AccountAuthInterceptor(context))
            .build()

        val apiService: AccountInfoApiInterface by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(AccountInfoApiInterface::class.java)

        }

}