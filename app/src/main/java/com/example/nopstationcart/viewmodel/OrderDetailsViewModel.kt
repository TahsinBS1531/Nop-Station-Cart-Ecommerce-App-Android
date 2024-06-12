package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Local.OrderDetailsEntity
import com.example.nopstationcart.Services.Repository.OrderDetailsRepository
import kotlinx.coroutines.launch

class OrderDetailsViewModel():ViewModel() {


    private val _orderDetails = MutableLiveData<List<OrderDetailsEntity>>()
    val orderDetails: LiveData<List<OrderDetailsEntity>> get() = _orderDetails

    suspend fun saveOrderDetails(context: Context, orderDetails:OrderDetailsEntity){
        val orderDao = AppDatabase.getDatabase(context).orderDetailsDao()
        val repository = OrderDetailsRepository(orderDao)
        viewModelScope.launch {
            println("Order Details Data is saved")
            repository.insertOrderDetails(orderDetails)
        }
    }

    suspend fun getOrderDetails(userEmail:String,context:Context){
        val orderDao = AppDatabase.getDatabase(context).orderDetailsDao()
        val repo = OrderDetailsRepository(orderDao)
        viewModelScope.launch {
            val orders = repo.getOrderDetails(userEmail)
            _orderDetails.postValue(orders)
        }
    }
}