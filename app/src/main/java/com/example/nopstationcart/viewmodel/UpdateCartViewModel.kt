package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartRequest
import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartResponse
import com.example.nopstationcart.Services.Repository.UpdateCartRepository

class UpdateCartViewModel:ViewModel() {
    private val _response= MutableLiveData<Result<UpdateCartResponse>>()
    val response : LiveData<Result<UpdateCartResponse>> = _response

    fun getApiCall(request: UpdateCartRequest, context:Context){
        val repository = UpdateCartRepository()
        val result = repository.getUpdatedCart(request,context)
        result.observeForever { _response.postValue(it) }
    }
}