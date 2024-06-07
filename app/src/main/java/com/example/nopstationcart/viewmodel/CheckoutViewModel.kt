package com.example.nopstationcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Checkout.CheckoutResponse
import com.example.nopstationcart.Services.Model.logout.LogOutResponse
import com.example.nopstationcart.Services.Repository.CheckoutRepository

class CheckoutViewModel:ViewModel() {

    private val repository = CheckoutRepository()
    private val _response = MutableLiveData<Result<CheckoutResponse>>()
    val result: LiveData<Result<CheckoutResponse>> = _response

    fun getResponse(){
        val result1 = repository.getCheckoutRepo()
        result1.observeForever { _response.postValue(it) }
    }
}