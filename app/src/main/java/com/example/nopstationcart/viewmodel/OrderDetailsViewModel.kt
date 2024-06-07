package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Local.OrderDetailsEntity
import com.example.nopstationcart.Services.Repository.OrderDetailsRepository
import kotlinx.coroutines.launch

class OrderDetailsViewModel():ViewModel() {
    suspend fun saveOrderDetails(context: Context, orderDetails:OrderDetailsEntity){
        val orderDao = AppDatabase.getDatabase(context).orderDetailsDao()
        val repository = OrderDetailsRepository(orderDao)
        viewModelScope.launch {
            repository.insertOrderDetails(orderDetails)
        }
    }
}