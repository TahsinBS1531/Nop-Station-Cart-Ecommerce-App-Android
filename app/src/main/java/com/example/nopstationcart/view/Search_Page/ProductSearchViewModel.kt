package com.example.nopstationcart.view.Search_Page

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.Model.Product_Search.ProductSearchResponse
import com.example.nopstationcart.Services.Repository.ProductSearchRepository
import com.example.nopstationcart.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(private val repository:ProductSearchRepository):ViewModel() {
    val responseLiveData:LiveData<NetworkResult<ProductSearchResponse>>
        get() = repository.responseLiveData
    fun searchProducts(query:String){
        viewModelScope.launch {
            repository.productSearch(query)
        }
    }
}