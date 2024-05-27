package com.example.nopstationcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Model.Cart.FormValue
import com.example.nopstationcart.Services.Repository.CartPageRepository

class CartViewModel():ViewModel(){
    lateinit var form: List<FormValue>


    private val _response = MutableLiveData<Result<CartResponse>>()
    val result:LiveData<Result<CartResponse>> = _response

    fun getCartResponse(id:Int){
        form = listOf(FormValue("addtocart_12020.EnteredQuantity","1"),
            FormValue("addtocart_12020.EnteredGender","Male"))
        val requestBody = CartBodyRequest(form)

        var repository = CartPageRepository(id)

        val result = repository.addProductCart(requestBody)
        result.observeForever { _response.postValue(it) }
    }

}