package com.example.nopstationcart.Services

import com.example.nopstationcart.Services.Model.Home_Page.Slider.sliderAuthInterceptor
import com.example.nopstationcart.Services.Model.login.loginAuthInterceptor
import com.example.nopstationcart.Services.Netwrok.loginApiInterface
import com.example.nopstationcart.Services.Netwrok.sliderApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SliderApiClient {

    private const val BASE_URL = "http://demo460.nop-station.com/api/"
    val client = OkHttpClient.Builder()
        .addInterceptor(sliderAuthInterceptor())
        .build()

    val apiService: sliderApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(sliderApiInterface::class.java)
    }
}