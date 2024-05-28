package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartInstance
import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartRequest
import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartResponse
import com.example.nopstationcart.Services.Netwrok.UpdateCartApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateCartRepository {

    fun getUpdatedCart(request:UpdateCartRequest):LiveData<Result<UpdateCartResponse>>{
        val _result = MutableLiveData<Result<UpdateCartResponse>>()
        val instance = UpdateCartInstance.retrofit.create(UpdateCartApiInterface::class.java)
        val call = instance.updateCart(request)
        call.enqueue(object:Callback<UpdateCartResponse>{
            override fun onResponse(p0: Call<UpdateCartResponse>, response: Response<UpdateCartResponse>){
                if(response.isSuccessful){
                    response.body()?.let {
                        _result.postValue(Result.success(it))
                    }?:_result.postValue(Result.failure(Throwable("Response Body is Null")))
                }else{
                    _result.postValue(Result.failure(Throwable("request is denied")))
                }
            }

            override fun onFailure(p0: Call<UpdateCartResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("request is denied")))
            }

        })

        return _result
    }
}