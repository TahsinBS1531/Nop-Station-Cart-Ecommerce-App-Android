package com.example.nopstationcart.Services.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Model.Cart.CartretrofitInstance
import com.example.nopstationcart.Services.Model.Cart.FormValue
import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Netwrok.AddToCartApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.Normalizer.Form

class CartPageRepository(val productID:Int) {


    fun addProductCart(request: CartBodyRequest,context:Context):LiveData<Result<CartResponse>>{
        val call = CartretrofitInstance.getretrofit(context).create(AddToCartApiInterface::class.java).getAddToCartApi(productID,request)
        val _result = MutableLiveData<Result<CartResponse>>()
        call.enqueue(object: Callback<CartResponse>{
            override fun onResponse(p0: Call<CartResponse>, response: Response<CartResponse>) {
                if(response.isSuccessful){

                    response.body()?.let {
                        if(response.code() ==400){
                            _result.postValue(Result.failure(Throwable("This can't be added")))
                        }else{
                            _result.postValue(Result.success(it))
                        }
                    }?:_result.postValue(Result.failure(Throwable("Response Body is Null")))
                }
                else{
                    val errorMessage = when (response.code()) {
                        400 -> "This product cannot be added to the cart"
                        else -> response.errorBody()?.string() ?: "Unknown error occurred"
                    }
                    _result.postValue(Result.failure(Throwable(errorMessage)))
                }
            }

            override fun onFailure(p0: Call<CartResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("request is denied")))
            }

        })
        return _result
    }
}