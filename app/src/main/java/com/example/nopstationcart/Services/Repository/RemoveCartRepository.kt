package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Model.Cart.CartretrofitInstance
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartInstance
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartRequest
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartResponse
import com.example.nopstationcart.Services.Netwrok.RemoveCartApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class RemoveCartRepository {

    fun addProductCart(request: RemoveCartRequest): LiveData<Result<RemoveCartResponse>> {
        val instance = RemoveCartInstance.retrofit.create(RemoveCartApiInterface::class.java)
        val call = instance.getRemoveCart(request)

        val _result = MutableLiveData<Result<RemoveCartResponse>>()
        call.enqueue(object: Callback<RemoveCartResponse> {
            override fun onResponse(p0: Call<RemoveCartResponse>, response: Response<RemoveCartResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        _result.postValue(Result.success(it))
                    }?:_result.postValue(Result.failure(Throwable("Response Body is Null")))
                }else{
                    _result.postValue(Result.failure(Throwable("request is denied")))
                }
            }

            override fun onFailure(p0: Call<RemoveCartResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("request is denied")))
            }

        })
        return _result
    }
}