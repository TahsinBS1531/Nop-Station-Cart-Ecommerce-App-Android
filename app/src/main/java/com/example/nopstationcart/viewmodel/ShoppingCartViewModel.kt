package com.example.nopstationcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.ShoppingCart.CartProductsResponse
import com.example.nopstationcart.Services.Repository.ShoppingCartRepository

class ShoppingCartViewModel:ViewModel() {

    private val _response = MutableLiveData<Result<CartProductsResponse>>()
    val response: LiveData<Result<CartProductsResponse>> =_response

    fun getCartProducts(){
        val repository = ShoppingCartRepository()
        val result = repository.getShoppingCartData()
        result.observeForever { _response.postValue(it) }
    }
}