package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.Checkout.CheckoutResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface CheckoutApiInterface {

    @GET("confirmorder")
    fun getCheckOut(): Call<CheckoutResponse>
}