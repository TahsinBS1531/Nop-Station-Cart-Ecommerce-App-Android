package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartRequest
import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UpdateCartApiInterface {
    @POST("shoppingcart/updatecart")
    fun updateCart(@Body request:UpdateCartRequest):Call<UpdateCartResponse>
}