package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Netwrok.categoryListApiInterface
import com.example.nopstationcart.Services.Netwrok.featuredProductsApiInterface
import com.example.nopstationcart.Services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryListRepository {
    val instance = RetrofitInstance()

    fun getData():LiveData<Result<CategoryListResponse>>{
        val _result = MutableLiveData<Result<CategoryListResponse>>()

        val call = instance.getRetrofitInstance().create(categoryListApiInterface::class.java).getApiData()
        call.enqueue(object:Callback<CategoryListResponse>{
            override fun onResponse(p0: Call<CategoryListResponse>, response: Response<CategoryListResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {products->
                        _result.postValue(Result.success(products))
                    }?: _result.postValue(Result.failure(Throwable("Response body is null")))
                }else{
                    _result.postValue(Result.failure(Throwable("Request is denied ")))
                }
            }

            override fun onFailure(p0: Call<CategoryListResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("Request is failed already ")))
            }

        })
        return _result
    }

}