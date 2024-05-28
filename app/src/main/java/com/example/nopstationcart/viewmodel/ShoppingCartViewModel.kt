package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.ShoppingCart.CartProductsResponse
import com.example.nopstationcart.Services.Repository.ShoppingCartRepository

class ShoppingCartViewModel:ViewModel() {

    private val _response = MutableLiveData<Result<CartProductsResponse>>()
    val response: LiveData<Result<CartProductsResponse>> =_response

    fun getCartProducts(context:Context){
        val repository = ShoppingCartRepository()
        val result = repository.getShoppingCartData(context)
        result.observeForever { _response.postValue(it) }
    }
}