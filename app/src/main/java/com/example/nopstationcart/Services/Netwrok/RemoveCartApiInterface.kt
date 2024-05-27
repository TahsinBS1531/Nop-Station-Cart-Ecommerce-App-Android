package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartRequest
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoveCartApiInterface {
    @POST("shoppingcart/updatecart")
    fun getRemoveCart(@Body request:RemoveCartRequest): Call<RemoveCartResponse>
}