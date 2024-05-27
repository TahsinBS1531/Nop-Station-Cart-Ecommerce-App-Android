package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AddToCartApiInterface {
    @POST("shoppingCart/AddProductToCart/details/{productId}/1")
    fun getAddToCartApi(@Path("productId") productId:Int, @Body request:CartBodyRequest): Call<CartResponse>
}