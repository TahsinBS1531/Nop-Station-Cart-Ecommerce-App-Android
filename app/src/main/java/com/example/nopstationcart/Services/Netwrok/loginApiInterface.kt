package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.login.loginDataClass
import com.example.nopstationcart.Services.Model.login.loginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface loginApiInterface {
    @POST("customer/login")
    //fun getApiData(@Body request: loginDataClass ):Response<loginResponse>
    fun getApiData(@Body request: loginDataClass ):Call<loginResponse>
}