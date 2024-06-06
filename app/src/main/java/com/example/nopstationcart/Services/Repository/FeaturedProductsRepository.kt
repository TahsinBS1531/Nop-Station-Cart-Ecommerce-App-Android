package com.example.nopstationcart.Services.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Local.FeaturedProductsDao
import com.example.nopstationcart.Services.Local.FeaturedProductsEntity
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Netwrok.InternetStatus
import com.example.nopstationcart.Services.Netwrok.featuredProductsApiInterface
import com.example.nopstationcart.Services.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeaturedProductsRepository {
    val instance = RetrofitInstance()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getData(context:Context, featuredProductsDao:FeaturedProductsDao):LiveData<Result<List<FeaturedProductsEntity>>>{
        val _result = MutableLiveData<Result<List<FeaturedProductsEntity>>>()

        if(InternetStatus.isOnline(context)){
            val call = instance.getRetrofitInstance().create(featuredProductsApiInterface::class.java).getApiData()
            call.enqueue(object:Callback<FeaturedProductsResponse>{
                override fun onResponse(p0: Call<FeaturedProductsResponse>, response: Response<FeaturedProductsResponse>) {
                    if(response.isSuccessful){
                        response.body()?.let {productResponse->
                            val products = productResponse.Data.map {product->
                                FeaturedProductsEntity(id = product.Id,
                                    name = product.Name, price = product.ProductPrice.Price,
                                    imageUrl = product.PictureModels[0].ImageUrl,
                                    rating = if (product.ReviewOverviewModel.TotalReviews != 0) {
                                        (product.ReviewOverviewModel.RatingSum / product.ReviewOverviewModel.TotalReviews).toFloat()
                                    } else {
                                        0f
                                    },
                                    shortDescription = product.ShortDescription,
                                    longDescription = product.FullDescription,
                                    oldPrice = product.ProductPrice.OldPrice ?: "0.0"   )
                            }
                            coroutineScope.launch {
                                featuredProductsDao.deleteAll()
                                featuredProductsDao.insertAll(products)

                                withContext(Dispatchers.Main){
                                    _result.postValue(Result.success(products))
                                }
                            }


                        }?: _result.postValue(Result.failure(Throwable("Response body is null")))
                    }else{
                        _result.postValue(Result.failure(Throwable("Request is denied ")))
                    }
                }

                override fun onFailure(p0: Call<FeaturedProductsResponse>, p1: Throwable) {
                    _result.postValue(Result.failure(Throwable("Request is failed already ")))
                }

            })
        }else{
            getDataFromLocal(_result,featuredProductsDao)
        }


        return _result
    }

    private fun getDataFromLocal(_result : MutableLiveData<Result<List<FeaturedProductsEntity>>>,featuredProductsDao:FeaturedProductsDao){
        coroutineScope.launch {
            try {
                val localData = featuredProductsDao.getAllFeaturedProducts()
                withContext(Dispatchers.Main){
                    _result.postValue(Result.success(localData))
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main) {
                    _result.postValue(Result.failure(e))
                }
            }
        }
    }

}