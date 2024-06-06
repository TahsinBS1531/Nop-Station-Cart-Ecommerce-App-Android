package com.example.nopstationcart.viewmodel

import android.content.Context
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

    fun getCartResponse(id:Int,context:Context,quantity:String){
        form = listOf(FormValue("addtocart_${id}.EnteredQuantity",quantity),
            FormValue("addtocart_${id}.EnteredGender","Male"))
        val requestBody = CartBodyRequest(form)
        var repository = CartPageRepository(id)

        val result = repository.addProductCart(requestBody,context)
        result.observeForever { _response.postValue(it) }
    }

}