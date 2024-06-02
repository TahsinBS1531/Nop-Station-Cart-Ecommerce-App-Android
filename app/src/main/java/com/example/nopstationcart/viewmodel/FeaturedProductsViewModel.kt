package com.example.nopstationcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Repository.FeaturedProductsRepository

class FeaturedProductsViewModel:ViewModel() {
    val repo = FeaturedProductsRepository()
    private val _result = MutableLiveData<Result<FeaturedProductsResponse>>()
    val result: LiveData<Result<FeaturedProductsResponse>> = _result

    fun getFeaturedProducts(){
        val result = repo.getData()
        result.observeForever { _result.postValue(it) }
    }

}