package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Repository.CategoryListRepository
import com.example.nopstationcart.Services.Repository.FeaturedProductsRepository

class CategoryListViewModel() : ViewModel() {
    val repo = CategoryListRepository()
    private val _result = MutableLiveData<Result<CategoryListResponse>>()
    val result: LiveData<Result<CategoryListResponse>> = _result


    fun getCategoryList(context:Context){
        val result = repo.getData(context)
        result.observeForever { _result.postValue(it) }
    }



}