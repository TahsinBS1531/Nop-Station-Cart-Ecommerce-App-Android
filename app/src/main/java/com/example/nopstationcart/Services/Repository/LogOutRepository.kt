package com.example.nopstationcart.Services.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Model.Cart.CartretrofitInstance
import com.example.nopstationcart.Services.Model.Cart.FormValue
import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Model.logout.LogOutResponse
import com.example.nopstationcart.Services.Model.logout.LogOutRetrofitInstance
import com.example.nopstationcart.Services.Netwrok.logOutApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.Normalizer.Form

class LogOutRepository() {

    fun logOutRepo(context:Context):LiveData<Result<LogOutResponse>>{
        val call = LogOutRetrofitInstance(context).retrofit.create(logOutApiInterface::class.java).logOut()
        val _result = MutableLiveData<Result<LogOutResponse>>()

        call.enqueue(object: Callback<LogOutResponse>{
            override fun onResponse(p0: Call<LogOutResponse>, response: Response<LogOutResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        _result.postValue(Result.success(it))
                    }?:_result.postValue(Result.failure(Throwable("Response Body is Null")))
                }else{
                    _result.postValue(Result.failure(Throwable("request is denied")))
                }
            }

            override fun onFailure(p0: Call<LogOutResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("request is denied")))
            }

        })
        return _result
    }
}