package com.example.nopstationcart.view.Account

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.AccountInfo.AccountInfoResponse
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Model.Cart.FormValue
import com.example.nopstationcart.Services.Model.logout.LogOutResponse
import com.example.nopstationcart.Services.Repository.AccountInfoRepository
import com.example.nopstationcart.Services.Repository.CartPageRepository
import com.example.nopstationcart.Services.Repository.LogOutRepository

class AccountInfoViewModel():ViewModel(){


    private val _response = MutableLiveData<Result<AccountInfoResponse>>()
    val result:LiveData<Result<AccountInfoResponse>> = _response

    fun getAccountResponse(context:Context){
        var repository = AccountInfoRepository()
        val result = repository.AccountInfoRepo(context)
        result.observeForever { _response.postValue(it) }
    }

}