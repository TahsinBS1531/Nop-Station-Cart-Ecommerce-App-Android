package com.example.nopstationcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartRequest
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartResponse
import com.example.nopstationcart.Services.Repository.RemoveCartRepository

class RemoveCartViewModel :ViewModel() {

    private val _response= MutableLiveData<Result<RemoveCartResponse>>()
    val response: LiveData<Result<RemoveCartResponse>> = _response

    fun getTheCartRemoved(request : RemoveCartRequest){
        val repository = RemoveCartRepository()
        val result = repository.addProductCart(request)

        result.observeForever { _response.postValue(it) }

    }
}