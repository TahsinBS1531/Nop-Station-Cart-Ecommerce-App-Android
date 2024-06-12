package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.CheckoutRetrofitInstance
import com.example.nopstationcart.Services.Model.Checkout.CheckoutResponse
import com.example.nopstationcart.Services.Model.logout.LogOutResponse
import com.example.nopstationcart.Services.Netwrok.CheckoutApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutRepository {

    fun getCheckoutRepo():LiveData<Result<CheckoutResponse>>{
        val _result = MutableLiveData<Result<CheckoutResponse>>()
        val call = CheckoutRetrofitInstance().getRetrofitInstance().create(CheckoutApiInterface::class.java).getCheckOut()
        call.enqueue(object: Callback<CheckoutResponse>{
            override fun onResponse(p0: Call<CheckoutResponse>, response: Response<CheckoutResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        _result.postValue(Result.success(it))
                        println("Checkout Api Successful")
                    }?:_result.postValue(Result.failure(Throwable("Response Body is Null")))
                }else{
                    _result.postValue(Result.failure(Throwable("request is denied")))
                    println("Checkout Api Not Successful")
                }
            }

            override fun onFailure(p0: Call<CheckoutResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("request is denied")))
                println("Checkout Api unsuccessful")
            }

        })

        return _result
    }

}