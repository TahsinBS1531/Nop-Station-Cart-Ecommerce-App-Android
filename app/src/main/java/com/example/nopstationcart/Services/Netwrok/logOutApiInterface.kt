package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.logout.LogOutResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface logOutApiInterface {
    @GET("customer/logout")
    fun logOut():Call<LogOutResponse>
}