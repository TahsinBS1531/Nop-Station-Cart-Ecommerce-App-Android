package com.example.nopstationcart.Services.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.ShoppingCart.CartProductsResponse
import com.example.nopstationcart.Services.Model.ShoppingCart.ShoppingCartInstance
import com.example.nopstationcart.Services.Netwrok.ShoppingCartApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class ShoppingCartRepository {
    fun getShoppingCartData(context:Context):LiveData<Result<CartProductsResponse>>{
        val _result = MutableLiveData<Result<CartProductsResponse>>()
        val instance = ShoppingCartInstance.getRetrofit(context).create(ShoppingCartApiInterface::class.java)
        val call = instance.getCartData()
        call.enqueue(object: Callback<CartProductsResponse>{
            override fun onResponse(p0: Call<CartProductsResponse>, response: Response<CartProductsResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        _result.postValue(Result.success(it))
                    }?: _result.postValue(Result.failure(Throwable("Response body is null")))
                }else{
                    _result.postValue(Result.failure(Throwable("Error code: ${response.code()}")))
                }
            }

            override fun onFailure(p0: Call<CartProductsResponse>, p1: Throwable) {
                _result.postValue(Result.failure(p1))
            }

        })
        return _result
    }


}