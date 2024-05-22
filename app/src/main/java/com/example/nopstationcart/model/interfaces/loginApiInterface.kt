package com.example.nopstationcart.model.interfaces

import com.example.nopstationcart.model.data.login.loginDataClass
import com.example.nopstationcart.model.data.login.loginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface loginApiInterface {
    @POST("customer/login")
    //fun getApiData(@Body request: loginDataClass ):Response<loginResponse>
    fun getApiData(@Body request: loginDataClass ):Call<loginResponse>
}