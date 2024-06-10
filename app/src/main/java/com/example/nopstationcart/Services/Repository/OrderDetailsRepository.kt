package com.example.nopstationcart.Services.Repository

import android.content.Context
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Local.OrderDetailsDao
import com.example.nopstationcart.Services.Local.OrderDetailsEntity

class OrderDetailsRepository(private val orderDetailsDao: OrderDetailsDao) {

    suspend fun insertOrderDetails(orderDetails: OrderDetailsEntity) {
        orderDetailsDao.insertOrder(orderDetails)
    }

    suspend fun getOrderDetails(userEmail:String):List<OrderDetailsEntity>{
        val orders = orderDetailsDao.getOrder(userEmail)
        return orders
    }
}