package com.example.nopstationcart.Services.Netwrok

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface logOutApiInterface {
    @GET("customer/logout")
    fun logOut(@Header("Token") token:String,
               @Header("Content-Type") contentType:String,
               @Header("DeviceId") deviceID:String,
               @Header("NST") nst:String,
               @Header("User-Agent") userAgent:String):Call<Void>
}