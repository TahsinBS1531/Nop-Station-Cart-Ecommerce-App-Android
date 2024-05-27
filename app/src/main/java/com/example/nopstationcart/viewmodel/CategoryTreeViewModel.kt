package com.example.nopstationcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.example.nopstationcart.Services.Model.CategoryTree.CategoryTreeResponse
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Repository.CategoryListRepository
import com.example.nopstationcart.Services.Repository.CategoryTreeRepository
import com.example.nopstationcart.Services.Repository.FeaturedProductsRepository

class CategoryTreeViewModel() : ViewModel() {
    val repo = CategoryTreeRepository()
    private val _result = MutableLiveData<Result<CategoryTreeResponse>>()
    val result: LiveData<Result<CategoryTreeResponse>> = _result


    fun getCategoryList(){
        val result = repo.getData()
        result.observeForever { _result.postValue(it) }
    }



}