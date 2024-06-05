package com.example.nopstationcart.Services.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Netwrok.InternetStatus
import com.example.nopstationcart.Services.Netwrok.categoryListApiInterface
import com.example.nopstationcart.Services.Netwrok.featuredProductsApiInterface
import com.example.nopstationcart.Services.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryListRepository {
    val instance = RetrofitInstance()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getData(context:Context):LiveData<Result<CategoryListResponse>>{
        val _result = MutableLiveData<Result<CategoryListResponse>>()
        val call = instance.getRetrofitInstance().create(categoryListApiInterface::class.java).getApiData()
        val categoryListDao = AppDatabase.getDatabase(context).categoryDao()

        if(InternetStatus.isOnline(context)){
            call.enqueue(object:Callback<CategoryListResponse>{
                override fun onResponse(p0: Call<CategoryListResponse>, response: Response<CategoryListResponse>) {
                    if(response.isSuccessful){
                        response.body()?.let {products->
                            _result.postValue(Result.success(products))
                            coroutineScope.launch {
                                categoryListDao.deleteAll()
                                categoryListDao.insertCategory(products.Data)
                            }
                        }?: _result.postValue(Result.failure(Throwable("Response body is null")))
                    }else{
                        _result.postValue(Result.failure(Throwable("Request is denied ")))
                    }
                }

                override fun onFailure(p0: Call<CategoryListResponse>, p1: Throwable) {
                    _result.postValue(Result.failure(Throwable("Request is failed already ")))
                }

            })
        }else{
            println("Network is not available on the Category Page")
            coroutineScope.launch {
                try {
                    val localData = categoryListDao.getCategory()
                    val categoryListResponse = CategoryListResponse(Data = localData, ErrorList = emptyList(), Message = "Hello")
                    withContext(Dispatchers.Main){
                        _result.postValue(Result.success(categoryListResponse))
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main) {
                        _result.postValue(Result.failure(e))
                    }
                }

            }

        }

        return _result
    }


}