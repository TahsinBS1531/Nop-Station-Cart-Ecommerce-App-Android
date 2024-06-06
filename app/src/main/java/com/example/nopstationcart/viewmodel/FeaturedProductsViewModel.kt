package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Local.FeaturedProductsDao
import com.example.nopstationcart.Services.Local.FeaturedProductsEntity
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Repository.FeaturedProductsRepository

class FeaturedProductsViewModel:ViewModel() {
    val repo = FeaturedProductsRepository()

    private val _featuredProductsResult = MutableLiveData<Result<List<FeaturedProductsEntity>>>()
    val featuredProductsResult: LiveData<Result<List<FeaturedProductsEntity>>> = _featuredProductsResult

    fun getFeaturedProducts(context:Context){
        val featuredProductsDao = AppDatabase.getDatabase(context).featuredProductDao()
        val result = repo.getData(context,featuredProductsDao)
        result.observeForever { _featuredProductsResult.postValue(it) }
    }

}