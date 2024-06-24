package com.example.nopstationcart.view.Account

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Model.Cart.FormValue
import com.example.nopstationcart.Services.Model.logout.LogOutResponse
import com.example.nopstationcart.Services.Repository.CartPageRepository
import com.example.nopstationcart.Services.Repository.LogOutRepository

class LogOutViewModel():ViewModel(){


    private val _response = MutableLiveData<Result<LogOutResponse>>()
    val result:LiveData<Result<LogOutResponse>> = _response

    fun getCartResponse(context:Context){

        var repository = LogOutRepository()

        val result = repository.logOutRepo(context)
        result.observeForever { _response.postValue(it) }
    }

}