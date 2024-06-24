package com.example.nopstationcart.Services

import com.example.nopstationcart.Services.Model.login.loginAuthInterceptor
import com.example.nopstationcart.Services.Netwrok.loginApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginApiClient{
    private const val BASE_URL = "http://demo460.nop-station.com/api/"
    val client = OkHttpClient.Builder()
        .addInterceptor(
            loginAuthInterceptor(
            deviceId = "44b4d8cd-7a2d-4a5f-a0e2-798021f1e294", // Provide actual device id
            nst = "eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw", // Provide actual NST token
            userAgent = "com.bs.ecommerce/1.0" // Provide actual user agent
        )
        )
        .build()

    val apiService: loginApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(loginApiInterface::class.java)

    }

}