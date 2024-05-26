package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.example.nopstationcart.Services.Model.CategoryTree.CategoryTreeResponse
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Netwrok.categoryListApiInterface
import com.example.nopstationcart.Services.Netwrok.categoryTreeApiInterface
import com.example.nopstationcart.Services.Netwrok.featuredProductsApiInterface
import com.example.nopstationcart.Services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryTreeRepository {
    val instance = RetrofitInstance()

    fun getData():LiveData<Result<CategoryTreeResponse>>{
        val _result = MutableLiveData<Result<CategoryTreeResponse>>()

        val call = instance.getRetrofitInstance().create(categoryTreeApiInterface::class.java).getApiData()
        call.enqueue(object:Callback<CategoryTreeResponse>{
            override fun onResponse(p0: Call<CategoryTreeResponse>, response: Response<CategoryTreeResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {products->
                        _result.postValue(Result.success(products))
                    }?: _result.postValue(Result.failure(Throwable("Response body is null")))
                }else{
                    _result.postValue(Result.failure(Throwable("Request is denied ")))
                }
            }

            override fun onFailure(p0: Call<CategoryTreeResponse>, p1: Throwable) {
                _result.postValue(Result.failure(Throwable("Request is failed already ")))
            }

        })
        return _result
    }

}