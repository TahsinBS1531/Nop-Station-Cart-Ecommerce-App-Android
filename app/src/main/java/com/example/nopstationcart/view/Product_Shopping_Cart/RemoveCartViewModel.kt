package com.example.nopstationcart.view.Product_Shopping_Cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartRequest
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartResponse
import com.example.nopstationcart.Services.Repository.RemoveCartRepository

class RemoveCartViewModel :ViewModel() {

    private val _response= MutableLiveData<Result<RemoveCartResponse>>()
    val response: LiveData<Result<RemoveCartResponse>> = _response

    fun getTheCartRemoved(request : RemoveCartRequest, context:Context){
        val repository = RemoveCartRepository()
        val result = repository.addProductCart(request,context)
        result.observeForever { _response.postValue(it) }

    }
}