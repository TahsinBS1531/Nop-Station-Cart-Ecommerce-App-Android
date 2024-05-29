package com.example.nopstationcart.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Local.FeaturedProductsEntity
import com.example.nopstationcart.Services.Repository.FeaturedProductsRepository
import kotlinx.coroutines.launch

class FeaturedProductsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FeaturedProductsRepository
    val featuredProducts: LiveData<List<FeaturedProductsEntity>>

    init {
        val featuredProductDao = AppDatabase.getDatabase(application).featuredProductDao()
        repository = FeaturedProductsRepository(featuredProductDao, application)
        featuredProducts = repository.featuredProducts
        fetchAndCacheFeaturedProducts()
    }

    private fun fetchAndCacheFeaturedProducts() = viewModelScope.launch {
        repository.fetchAndCacheFeaturedProducts()
    }
}
