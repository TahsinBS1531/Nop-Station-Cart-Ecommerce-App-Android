package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Netwrok.featuredProductsApiInterface
import com.example.nopstationcart.Services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeaturedProductsRepository {
    val instance = RetrofitInstance()

    fun getData():LiveData<Result<FeaturedProductsResponse>>{
        val _result = MutableLiveData<Result<FeaturedProductsResponse>>()

        val call = instance.getRetrofitInstance().create(featuredProductsApiInterface::class.java).getApiData()
        call.enqueue(object:Callback<FeaturedProductsResponse>{
            override fun onResponse(p0: Call<FeaturedProductsResponse>, response: Response<FeaturedProductsResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {products->
                        _result.postValue(Result.success(products))
                    }?: _result.postValue(Result.failure(Throwable("Response body is null")))
                }else{
                    _result.postValue(Result.failure(Throwable("Request is denied ")))
                }
            }

            override fun onFailure(p0: Call<FeaturedProductsResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("Request is failed already ")))
            }

        })
        return _result
    }

}