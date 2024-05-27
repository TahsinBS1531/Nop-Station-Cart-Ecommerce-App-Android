package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.ShoppingCart.CartProductsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ShoppingCartApiInterface {

    @GET("shoppingcart/cart")
    fun getCartData():Call<CartProductsResponse>
}